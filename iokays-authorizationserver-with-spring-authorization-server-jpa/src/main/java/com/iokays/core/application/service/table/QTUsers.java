package com.iokays.core.application.service.table;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.*;
import com.querydsl.sql.ColumnMetadata;

import javax.annotation.processing.Generated;
import java.sql.Types;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QTUsers is a Querydsl query type for TUsers
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QTUsers extends com.querydsl.sql.RelationalPathBase<TUsers> {

    public static final QTUsers tUsers = new QTUsers("T_USERS");
    private static final long serialVersionUID = 222566799;
    public final NumberPath<Integer> concurrencyVersion = createNumber("concurrencyVersion", Integer.class);

    public final DateTimePath<java.sql.Timestamp> createdDate = createDateTime("createdDate", java.sql.Timestamp.class);

    public final BooleanPath enabled = createBoolean("enabled");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.sql.Timestamp> lastModifiedDate = createDateTime("lastModifiedDate", java.sql.Timestamp.class);

    public final StringPath password = createString("password");

    public final SimplePath<byte[]> userId = createSimple("userId", byte[].class);

    public final StringPath username = createString("username");

    public final com.querydsl.sql.PrimaryKey<TUsers> constraint27 = createPrimaryKey(id);

    public QTUsers(String variable) {
        super(TUsers.class, forVariable(variable), "PUBLIC", "T_USERS");
        addMetadata();
    }

    public QTUsers(String variable, String schema, String table) {
        super(TUsers.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QTUsers(String variable, String schema) {
        super(TUsers.class, forVariable(variable), schema, "T_USERS");
        addMetadata();
    }

    public QTUsers(Path<? extends TUsers> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "T_USERS");
        addMetadata();
    }

    public QTUsers(PathMetadata metadata) {
        super(TUsers.class, metadata, "PUBLIC", "T_USERS");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(concurrencyVersion, ColumnMetadata.named("CONCURRENCY_VERSION").withIndex(4).ofType(Types.INTEGER).withSize(32).notNull());
        addMetadata(createdDate, ColumnMetadata.named("CREATED_DATE").withIndex(2).ofType(Types.TIMESTAMP).withSize(26).withDigits(6).notNull());
        addMetadata(enabled, ColumnMetadata.named("ENABLED").withIndex(5).ofType(Types.BOOLEAN).withSize(1).notNull());
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.BIGINT).withSize(64).notNull());
        addMetadata(lastModifiedDate, ColumnMetadata.named("LAST_MODIFIED_DATE").withIndex(3).ofType(Types.TIMESTAMP).withSize(26).withDigits(6).notNull());
        addMetadata(password, ColumnMetadata.named("PASSWORD").withIndex(6).ofType(Types.VARCHAR).withSize(255));
        addMetadata(userId, ColumnMetadata.named("USER_ID").withIndex(7).ofType(Types.VARBINARY).withSize(255));
        addMetadata(username, ColumnMetadata.named("USERNAME").withIndex(8).ofType(Types.VARCHAR).withSize(255).notNull());
    }

}

