package com.iokays.dispatch.core.adapter.persistence.message.table;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QChannelMessage is a Querydsl query type for ChannelMessage
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QChannelMessage extends com.querydsl.sql.RelationalPathBase<ChannelMessage> {

    private static final long serialVersionUID = 2091482399;

    public static final QChannelMessage intChannelMessage = new QChannelMessage("int_channel_message");

    public final NumberPath<Long> createdDate = createNumber("createdDate", Long.class);

    public final StringPath groupKey = createString("groupKey");

    public final SimplePath<byte[]> messageBytes = createSimple("messageBytes", byte[].class);

    public final StringPath messageId = createString("messageId");

    public final NumberPath<Long> messagePriority = createNumber("messagePriority", Long.class);

    public final NumberPath<Long> messageSequence = createNumber("messageSequence", Long.class);

    public final StringPath region = createString("region");

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public final com.querydsl.sql.PrimaryKey<ChannelMessage> intChannelMessagePk = createPrimaryKey(region, groupKey, createdDate, messageSequence);

    public QChannelMessage(String variable) {
        super(ChannelMessage.class, forVariable(variable), "test-cdewsij", "int_channel_message");
        addMetadata();
    }

    public QChannelMessage(String variable, String schema, String table) {
        super(ChannelMessage.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QChannelMessage(String variable, String schema) {
        super(ChannelMessage.class, forVariable(variable), schema, "int_channel_message");
        addMetadata();
    }

    public QChannelMessage(Path<? extends ChannelMessage> path) {
        super(path.getType(), path.getMetadata(), "test-cdewsij", "int_channel_message");
        addMetadata();
    }

    public QChannelMessage(PathMetadata metadata) {
        super(ChannelMessage.class, metadata, "test-cdewsij", "int_channel_message");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(createdDate, ColumnMetadata.named("created_date").withIndex(3).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(groupKey, ColumnMetadata.named("group_key").withIndex(2).ofType(Types.CHAR).withSize(36).notNull());
        addMetadata(messageBytes, ColumnMetadata.named("message_bytes").withIndex(6).ofType(Types.BINARY).withSize(2147483647));
        addMetadata(messageId, ColumnMetadata.named("message_id").withIndex(1).ofType(Types.CHAR).withSize(36).notNull());
        addMetadata(messagePriority, ColumnMetadata.named("message_priority").withIndex(4).ofType(Types.BIGINT).withSize(19));
        addMetadata(messageSequence, ColumnMetadata.named("message_sequence").withIndex(5).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(region, ColumnMetadata.named("region").withIndex(7).ofType(Types.VARCHAR).withSize(100).notNull());
        addMetadata(status, ColumnMetadata.named("status").withIndex(8).ofType(Types.INTEGER).withSize(10));
    }

}

