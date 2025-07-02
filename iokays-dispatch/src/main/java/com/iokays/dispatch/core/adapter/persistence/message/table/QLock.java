package com.iokays.dispatch.core.adapter.persistence.message.table;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QLock is a Querydsl query type for Lock
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QLock extends com.querydsl.sql.RelationalPathBase<Lock> {

    private static final long serialVersionUID = 328529542;

    public static final QLock intLock = new QLock("int_lock");

    public final StringPath clientId = createString("clientId");

    public final DateTimePath<java.sql.Timestamp> createdDate = createDateTime("createdDate", java.sql.Timestamp.class);

    public final StringPath lockKey = createString("lockKey");

    public final StringPath region = createString("region");

    public final com.querydsl.sql.PrimaryKey<Lock> intLockPk = createPrimaryKey(lockKey, region);

    public QLock(String variable) {
        super(Lock.class, forVariable(variable), "test-cdewsij", "int_lock");
        addMetadata();
    }

    public QLock(String variable, String schema, String table) {
        super(Lock.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QLock(String variable, String schema) {
        super(Lock.class, forVariable(variable), schema, "int_lock");
        addMetadata();
    }

    public QLock(Path<? extends Lock> path) {
        super(path.getType(), path.getMetadata(), "test-cdewsij", "int_lock");
        addMetadata();
    }

    public QLock(PathMetadata metadata) {
        super(Lock.class, metadata, "test-cdewsij", "int_lock");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(clientId, ColumnMetadata.named("client_id").withIndex(3).ofType(Types.CHAR).withSize(36));
        addMetadata(createdDate, ColumnMetadata.named("created_date").withIndex(4).ofType(Types.TIMESTAMP).withSize(29).withDigits(6).notNull());
        addMetadata(lockKey, ColumnMetadata.named("lock_key").withIndex(1).ofType(Types.CHAR).withSize(36).notNull());
        addMetadata(region, ColumnMetadata.named("region").withIndex(2).ofType(Types.VARCHAR).withSize(100).notNull());
    }

}

