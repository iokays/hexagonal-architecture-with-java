package com.iokays.webflux.config.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.session.MapSession;
import org.springframework.session.ReactiveSessionRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

@Slf4j
@Component
public class R2dbcReactiveSessionRepository implements ReactiveSessionRepository<MapSession> {

    private static final String DEFAULT_TABLE_NAME = "SPRING_SESSION";
    private static final String DEFAULT_ATTRIBUTES_TABLE_NAME = "SPRING_SESSION_ATTRIBUTES";
    private static final Duration DEFAULT_MAX_INACTIVE_INTERVAL = Duration.ofSeconds(1800); // 30 minutes

    private final DatabaseClient databaseClient;

    // TODO 默认的序列化, 导致webflux无法反序列化,
    private final DeserializingConverter deserializingConverter = new DeserializingConverter();
    private final SerializingConverter serializingConverter = new SerializingConverter();

    // SQL queries (based on JdbcIndexedSessionRepository)
    private static final String INSERT_SESSION_QUERY =
            "INSERT INTO " + DEFAULT_TABLE_NAME + " (PRIMARY_ID, SESSION_ID, CREATION_TIME, LAST_ACCESS_TIME, MAX_INACTIVE_INTERVAL, EXPIRY_TIME, PRINCIPAL_NAME) " +
                    "VALUES (:primaryId, :sessionId, :creationTime, :lastAccessTime, :maxInactiveInterval, :expiryTime, :principalName)";

    private static final String INSERT_ATTRIBUTE_QUERY =
            "INSERT INTO " + DEFAULT_ATTRIBUTES_TABLE_NAME + " (SESSION_PRIMARY_ID, ATTRIBUTE_NAME, ATTRIBUTE_BYTES) " +
                    "VALUES (:sessionPrimaryId, :attributeName, :attributeValue)";

    private static final String SELECT_SESSION_QUERY =
            "SELECT s.PRIMARY_ID, s.SESSION_ID, s.CREATION_TIME, s.LAST_ACCESS_TIME, s.MAX_INACTIVE_INTERVAL, " +
                    "sa.ATTRIBUTE_NAME, sa.ATTRIBUTE_BYTES " +
                    "FROM " + DEFAULT_TABLE_NAME + " s " +
                    "LEFT JOIN " + DEFAULT_ATTRIBUTES_TABLE_NAME + " sa ON s.PRIMARY_ID = sa.SESSION_PRIMARY_ID " +
                    "WHERE s.SESSION_ID = :sessionId";

    private static final String UPDATE_SESSION_QUERY =
            "UPDATE " + DEFAULT_TABLE_NAME + " " +
                    "SET SESSION_ID = :sessionId, LAST_ACCESS_TIME = :lastAccessTime, " +
                    "MAX_INACTIVE_INTERVAL = :maxInactiveInterval, EXPIRY_TIME = :expiryTime, PRINCIPAL_NAME = :principalName " +
                    "WHERE PRIMARY_ID = :primaryId";

    private static final String UPDATE_ATTRIBUTE_QUERY =
            "UPDATE " + DEFAULT_ATTRIBUTES_TABLE_NAME + " " +
                    "SET ATTRIBUTE_BYTES = :attributeValue " +
                    "WHERE SESSION_PRIMARY_ID = :sessionPrimaryId AND ATTRIBUTE_NAME = :attributeName";

    private static final String DELETE_ATTRIBUTE_QUERY =
            "DELETE FROM " + DEFAULT_ATTRIBUTES_TABLE_NAME + " " +
                    "WHERE SESSION_PRIMARY_ID = :sessionPrimaryId AND ATTRIBUTE_NAME = :attributeName";

    private static final String DELETE_SESSION_QUERY =
            "DELETE FROM " + DEFAULT_TABLE_NAME + " " +
                    "WHERE SESSION_ID = :sessionId";

    public R2dbcReactiveSessionRepository(R2dbcEntityTemplate r2dbcEntityTemplate) {
        this.databaseClient = r2dbcEntityTemplate.getDatabaseClient();
    }

    @Override
    public Mono<MapSession> createSession() {
        return Mono.fromCallable(() -> {
            String sessionId = UUID.randomUUID().toString();
            String primaryId = UUID.randomUUID().toString();
            MapSession session = new MapSession(sessionId);
            session.setMaxInactiveInterval(DEFAULT_MAX_INACTIVE_INTERVAL);
            session.setCreationTime(Instant.now());
            session.setLastAccessedTime(Instant.now());
            return session;
        });
    }

    @Override
    public Mono<Void> save(MapSession session) {
        String primaryId = UUID.randomUUID().toString(); // Generate primary ID for new sessions
        Instant expiryTime = session.getLastAccessedTime().plus(session.getMaxInactiveInterval());

        // Check if session already exists (based on PRIMARY_ID or SESSION_ID)
        return databaseClient.sql(SELECT_SESSION_QUERY)
                .bind("sessionId", session.getId())
                .map(row -> row.get("PRIMARY_ID", String.class))
                .first()
                .flatMap(existingPrimaryId -> {
                    if (existingPrimaryId != null) {
                        // Update existing session
                        return updateSession(session, existingPrimaryId);
                    } else {
                        // Insert new session
                        return insertSession(session, primaryId);
                    }
                });
    }

    private Mono<Void> insertSession(MapSession session, String primaryId) {
        Instant expiryTime = session.getLastAccessedTime().plus(session.getMaxInactiveInterval());
        return databaseClient.sql(INSERT_SESSION_QUERY)
                .bind("primaryId", primaryId)
                .bind("sessionId", session.getId())
                .bind("creationTime", session.getCreationTime().toEpochMilli())
                .bind("lastAccessTime", session.getLastAccessedTime().toEpochMilli())
                        .bind("maxInactiveInterval", (int) session.getMaxInactiveInterval().getSeconds())
                        .bind("expiryTime", expiryTime.toEpochMilli())
                        .bindNull("principalName", String.class)
                        .then()
                        .thenMany(Flux.fromIterable(session.getAttributeNames())
                                .flatMap(attrName -> insertAttribute(primaryId, attrName, session.getAttribute(attrName))))
                        .then();
    }

    private Mono<Void> updateSession(MapSession session, String primaryId) {
        Instant expiryTime = session.getLastAccessedTime().plus(session.getMaxInactiveInterval());
        return databaseClient.sql(UPDATE_SESSION_QUERY)
                .bind("sessionId", session.getId())
                .bind("lastAccessTime", session.getLastAccessedTime().toEpochMilli())
                .bind("maxInactiveInterval", (int) session.getMaxInactiveInterval().getSeconds())
                .bind("expiryTime", expiryTime.toEpochMilli())
                .bindNull("principalName", String.class)
                .bind("primaryId", primaryId)
                .then()
                .thenMany(Flux.fromIterable(session.getAttributeNames())
                        .flatMap(attrName -> updateOrInsertAttribute(primaryId, attrName, session.getAttribute(attrName))))
                .then();
    }

    private Mono<Void> insertAttribute(String primaryId, String attrName, Object attrValue) {
        byte[] serializedValue = serializingConverter.convert(attrValue);
        return databaseClient.sql(INSERT_ATTRIBUTE_QUERY)
                .bind("sessionPrimaryId", primaryId)
                .bind("attributeName", attrName)
                .bind("attributeValue", serializedValue)
                .then();
    }

    private Mono<Void> updateOrInsertAttribute(String primaryId, String attrName, Object attrValue) {
        byte[] serializedValue = serializingConverter.convert(attrValue);
        return databaseClient.sql(UPDATE_ATTRIBUTE_QUERY)
                .bind("attributeValue", serializedValue)
                .bind("sessionPrimaryId", primaryId)
                .bind("attributeName", attrName)
                .fetch()
                .rowsUpdated()
                .flatMap(updated -> {
                    if (updated == 0) {
                        return insertAttribute(primaryId, attrName, attrValue);
                    }
                    return Mono.empty();
                });
    }

    @Override
    public Mono<MapSession> findById(String id) {
        log.info("findById: {}", id);
        return databaseClient.sql(SELECT_SESSION_QUERY)
                .bind("sessionId", id)
                .map(row -> {
                    String primaryId = row.get("PRIMARY_ID", String.class);
                    MapSession session = new MapSession(id);
                    session.setCreationTime(Instant.ofEpochMilli(row.get("CREATION_TIME", Long.class)));
                    session.setLastAccessedTime(Instant.ofEpochMilli(row.get("LAST_ACCESS_TIME", Long.class)));
                    session.setMaxInactiveInterval(Duration.ofSeconds(row.get("MAX_INACTIVE_INTERVAL", Integer.class)));

                    String attrName = row.get("ATTRIBUTE_NAME", String.class);
                    if (attrName != null) {
                        byte[] attrValue = row.get("ATTRIBUTE_BYTES", byte[].class);
                        Object deserializedValue = deserializingConverter.convert(attrValue);
                        session.setAttribute(attrName, deserializedValue);
                    }
                    return session;
                })
                .all()
                .collectList()
                .flatMap(sessions -> {
                    if (sessions.isEmpty()) {
                        return Mono.empty();
                    }
                    MapSession result = sessions.get(0);
                    if (result.isExpired()) {
                        return Mono.empty();
                    }
                    return Mono.just(result);
                });
    }

    @Override
    public Mono<Void> deleteById(String id) {
        log.info("deleteById: id={}", id);
        return databaseClient.sql(DELETE_SESSION_QUERY)
                .bind("sessionId", id)
                .then();
    }
}
