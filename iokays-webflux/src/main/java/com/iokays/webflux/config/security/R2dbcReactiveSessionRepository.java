package com.iokays.webflux.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.session.MapSession;
import org.springframework.session.ReactiveSessionRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.Map;

@Component
public class R2dbcReactiveSessionRepository implements ReactiveSessionRepository<MapSession> {

    private final DatabaseClient databaseClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public R2dbcReactiveSessionRepository(R2dbcEntityTemplate r2dbcEntityTemplate) {
        this.databaseClient = r2dbcEntityTemplate.getDatabaseClient();
    }

    @Override
    public Mono<MapSession> createSession() {
        return Mono.just(new MapSession());
    }

    @Override
    public Mono<Void> save(MapSession session) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public Mono<MapSession> findById(String id) {
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

    @Override
    public Mono<Void> deleteById(String id) {
        // 删除会话属性（由于外键约束，必须先删除属性）
        return databaseClient.sql("""
                        DELETE FROM SPRING_SESSION_ATTRIBUTES 
                        WHERE SESSION_PRIMARY_ID = (
                            SELECT PRIMARY_ID FROM SPRING_SESSION WHERE SESSION_ID = :sessionId
                        )
                        """)
                .bind("sessionId", id)
                .fetch()
                .rowsUpdated()
                .then(
                        // 删除主会话记录
                        databaseClient.sql("DELETE FROM SPRING_SESSION WHERE SESSION_ID = :sessionId")
                                .bind("sessionId", id)
                                .fetch()
                                .rowsUpdated()
                )
                .then();
    }
}
