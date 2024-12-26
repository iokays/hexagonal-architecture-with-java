package com.iokays.core.application.service.table;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.SimplePath;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.sql.ColumnMetadata;

import javax.annotation.processing.Generated;
import java.sql.Types;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QTUserLocalMessage is a Querydsl query type for TUserLocalMessage
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QTUserLocalMessage extends com.querydsl.sql.RelationalPathBase<TUserLocalMessage> {

    public static final QTUserLocalMessage tUserLocalMessage = new QTUserLocalMessage("T_USER_LOCAL_MESSAGE");
    private static final long serialVersionUID = -567391328;
    public final DateTimePath<java.sql.Timestamp> createdDate = createDateTime("createdDate", java.sql.Timestamp.class);

    public final StringPath deleted = createString("deleted");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.sql.Timestamp> lastModifiedDate = createDateTime("lastModifiedDate", java.sql.Timestamp.class);

    public final SimplePath<byte[]> messageBytes = createSimple("messageBytes", byte[].class);

    public final SimplePath<byte[]> messageId = createSimple("messageId", byte[].class);

    public final com.querydsl.sql.PrimaryKey<TUserLocalMessage> constraint468 = createPrimaryKey(id);

    public QTUserLocalMessage(String variable) {
        super(TUserLocalMessage.class, forVariable(variable), "PUBLIC", "T_USER_LOCAL_MESSAGE");
        addMetadata();
    }

    public QTUserLocalMessage(String variable, String schema, String table) {
        super(TUserLocalMessage.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QTUserLocalMessage(String variable, String schema) {
        super(TUserLocalMessage.class, forVariable(variable), schema, "T_USER_LOCAL_MESSAGE");
        addMetadata();
    }

    public QTUserLocalMessage(Path<? extends TUserLocalMessage> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "T_USER_LOCAL_MESSAGE");
        addMetadata();
    }

    public QTUserLocalMessage(PathMetadata metadata) {
        super(TUserLocalMessage.class, metadata, "PUBLIC", "T_USER_LOCAL_MESSAGE");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(createdDate, ColumnMetadata.named("CREATED_DATE").withIndex(2).ofType(Types.TIMESTAMP).withSize(26).withDigits(6).notNull());
        addMetadata(deleted, ColumnMetadata.named("DELETED").withIndex(6).ofType(Types.CHAR).withSize(1).notNull());
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.BIGINT).withSize(64).notNull());
        addMetadata(lastModifiedDate, ColumnMetadata.named("LAST_MODIFIED_DATE").withIndex(3).ofType(Types.TIMESTAMP).withSize(26).withDigits(6).notNull());
        addMetadata(messageBytes, ColumnMetadata.named("MESSAGE_BYTES").withIndex(4).ofType(Types.VARBINARY).withSize(255).notNull());
        addMetadata(messageId, ColumnMetadata.named("MESSAGE_ID").withIndex(5).ofType(Types.VARBINARY).withSize(255));
    }

}

