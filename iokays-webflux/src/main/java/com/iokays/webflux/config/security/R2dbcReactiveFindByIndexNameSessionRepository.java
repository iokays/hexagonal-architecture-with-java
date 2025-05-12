package com.iokays.webflux.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.session.MapSession;
import org.springframework.session.ReactiveFindByIndexNameSessionRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.Map;

@Slf4j
@Component
public class R2dbcReactiveFindByIndexNameSessionRepository implements ReactiveFindByIndexNameSessionRepository<MapSession> {

    private final DatabaseClient databaseClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public R2dbcReactiveFindByIndexNameSessionRepository(R2dbcEntityTemplate r2dbcEntityTemplate) {
        this.databaseClient = r2dbcEntityTemplate.getDatabaseClient();
    }

    @Override
    public Mono<Map<String, MapSession>> findByIndexNameAndIndexValue(
            String indexName, String indexValue) {

        if (!PRINCIPAL_NAME_INDEX_NAME.equals(indexName)) {
            return Mono.empty();
        }

        log.info("findByIndexNameAndIndexValue: indexName={}, indexValue={}", indexName, indexValue);

        return databaseClient.sql("""
                        SELECT SESSION_ID, CREATION_TIME, LAST_ACCESS_TIME, 
                               MAX_INACTIVE_INTERVAL, EXPIRY_TIME
                        FROM SPRING_SESSION
                        WHERE PRINCIPAL_NAME = :principalName
                        AND EXPIRY_TIME > :currentTime
                        """)
                .bind("principalName", indexValue)
                .bind("currentTime", System.currentTimeMillis())
                .fetch()
                .all()
                .collectMap(
                        row -> (String) row.get("SESSION_ID"),
                        row -> {
                            MapSession session = new MapSession((String) row.get("SESSION_ID"));
                            session.setCreationTime(Instant.ofEpochSecond(((Number) row.get("CREATION_TIME")).longValue()));
                            session.setLastAccessedTime(Instant.ofEpochSecond(((Number) row.get("LAST_ACCESS_TIME")).longValue()));
                            session.setMaxInactiveInterval(
                                    Duration.ofSeconds(((Number) row.get("MAX_INACTIVE_INTERVAL")).intValue())
                            );
                            return session;
                        }
                )
                .flatMap(sessionsMap -> {
                    if (sessionsMap.isEmpty()) {
                        return Mono.just(Collections.emptyMap());
                    }

                    // 加载每个会话的属性
                    return Flux.fromIterable(sessionsMap.keySet())
                            .flatMap(sessionId -> findById(sessionId)
                                    .map(session -> Tuples.of(sessionId, session))
                            )
                            .collectMap(
                                    Tuple2::getT1,
                                    Tuple2::getT2
                            );
                });
    }

    public Mono<MapSession> findById(String id) {
        log.info("findById: id={}", id);
        // 查询主会话信息
        Mono<MapSession> sessionMono = databaseClient.sql("""
                        SELECT PRIMARY_ID, SESSION_ID, CREATION_TIME, LAST_ACCESS_TIME, 
                               MAX_INACTIVE_INTERVAL, EXPIRY_TIME, PRINCIPAL_NAME 
                        FROM SPRING_SESSION 
                        WHERE SESSION_ID = :sessionId
                        """)
                .bind("sessionId", id)
                .fetch()
                .one()
                .map(row -> {
                    MapSession session = new MapSession();
                    session.setId((String) row.get("SESSION_ID"));
                    session.setCreationTime(Instant.ofEpochSecond(((Number) row.get("CREATION_TIME")).longValue()));
                    session.setLastAccessedTime(Instant.ofEpochSecond(((Number) row.get("LAST_ACCESS_TIME")).longValue()));
                    session.setMaxInactiveInterval(java.time.Duration.ofSeconds(
                            ((Number) row.get("MAX_INACTIVE_INTERVAL")).intValue()
                    ));
                    return session;
                });

        // 查询会话属性
        Mono<Map<String, Object>> attributesMono = databaseClient.sql("""
                        SELECT ATTRIBUTE_NAME, ATTRIBUTE_BYTES 
                        FROM SPRING_SESSION_ATTRIBUTES 
                        WHERE SESSION_PRIMARY_ID = (
                            SELECT PRIMARY_ID FROM SPRING_SESSION WHERE SESSION_ID = :sessionId
                        )
                        """)
                .bind("sessionId", id)
                .fetch()
                .all()
                .collectMap(
                        row -> (String) row.get("ATTRIBUTE_NAME"),
                        row -> {
                            try {
                                byte[] bytes = (byte[]) row.get("ATTRIBUTE_BYTES");
                                return objectMapper.readValue(bytes, Object.class);
                            } catch (Exception e) {
                                throw new RuntimeException("Failed to deserialize session attribute", e);
                            }
                        }
                );

        // 合并结果
        return Mono.zip(sessionMono, attributesMono)
                .map(tuple -> {
                    MapSession session = tuple.getT1();
                    Map<String, Object> attributes = tuple.getT2();
                    attributes.forEach(session::setAttribute);
                    return session;
                });
    }
}
