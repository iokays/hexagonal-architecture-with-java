package com.iokays.dispatch.core.adapter.persistence.message.table;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.SimplePath;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.sql.ColumnMetadata;

import javax.annotation.processing.Generated;
import java.sql.Types;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QMessage is a Querydsl query type for Message
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QMessage extends com.querydsl.sql.RelationalPathBase<Message> {

    public static final QMessage intMessage = new QMessage("int_message");
    private static final long serialVersionUID = -390558516;
    public final DateTimePath<java.sql.Timestamp> createdDate = createDateTime("createdDate", java.sql.Timestamp.class);

    public final SimplePath<byte[]> messageBytes = createSimple("messageBytes", byte[].class);

    public final StringPath messageId = createString("messageId");

    public final StringPath region = createString("region");

    public final com.querydsl.sql.PrimaryKey<Message> intMessagePk = createPrimaryKey(messageId, region);

    public QMessage(String variable) {
        super(Message.class, forVariable(variable), "test-cdewsij", "int_message");
        addMetadata();
    }

    public QMessage(String variable, String schema, String table) {
        super(Message.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QMessage(String variable, String schema) {
        super(Message.class, forVariable(variable), schema, "int_message");
        addMetadata();
    }

    public QMessage(Path<? extends Message> path) {
        super(path.getType(), path.getMetadata(), "test-cdewsij", "int_message");
        addMetadata();
    }

    public QMessage(PathMetadata metadata) {
        super(Message.class, metadata, "test-cdewsij", "int_message");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(createdDate, ColumnMetadata.named("created_date").withIndex(3).ofType(Types.TIMESTAMP).withSize(29).withDigits(6).notNull());
        addMetadata(messageBytes, ColumnMetadata.named("message_bytes").withIndex(4).ofType(Types.BINARY).withSize(2147483647));
        addMetadata(messageId, ColumnMetadata.named("message_id").withIndex(1).ofType(Types.CHAR).withSize(36).notNull());
        addMetadata(region, ColumnMetadata.named("region").withIndex(2).ofType(Types.VARCHAR).withSize(100).notNull());
    }

}

