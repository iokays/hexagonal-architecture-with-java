package com.iokays.dispatch.core.adapter.persistence.message.table;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QGroupToMessage is a Querydsl query type for GroupToMessage
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QGroupToMessage extends com.querydsl.sql.RelationalPathBase<GroupToMessage> {

    private static final long serialVersionUID = 451009768;

    public static final QGroupToMessage intGroupToMessage = new QGroupToMessage("int_group_to_message");

    public final StringPath groupKey = createString("groupKey");

    public final StringPath messageId = createString("messageId");

    public final StringPath region = createString("region");

    public final com.querydsl.sql.PrimaryKey<GroupToMessage> intGroupToMessagePk = createPrimaryKey(groupKey, messageId, region);

    public QGroupToMessage(String variable) {
        super(GroupToMessage.class, forVariable(variable), "test-cdewsij", "int_group_to_message");
        addMetadata();
    }

    public QGroupToMessage(String variable, String schema, String table) {
        super(GroupToMessage.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QGroupToMessage(String variable, String schema) {
        super(GroupToMessage.class, forVariable(variable), schema, "int_group_to_message");
        addMetadata();
    }

    public QGroupToMessage(Path<? extends GroupToMessage> path) {
        super(path.getType(), path.getMetadata(), "test-cdewsij", "int_group_to_message");
        addMetadata();
    }

    public QGroupToMessage(PathMetadata metadata) {
        super(GroupToMessage.class, metadata, "test-cdewsij", "int_group_to_message");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(groupKey, ColumnMetadata.named("group_key").withIndex(1).ofType(Types.CHAR).withSize(36).notNull());
        addMetadata(messageId, ColumnMetadata.named("message_id").withIndex(2).ofType(Types.CHAR).withSize(36).notNull());
        addMetadata(region, ColumnMetadata.named("region").withIndex(3).ofType(Types.VARCHAR).withSize(100).notNull());
    }

}

