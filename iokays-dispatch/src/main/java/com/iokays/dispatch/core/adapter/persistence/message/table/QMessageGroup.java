package com.iokays.dispatch.core.adapter.persistence.message.table;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.sql.ColumnMetadata;

import javax.annotation.processing.Generated;
import java.sql.Types;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QMessageGroup is a Querydsl query type for MessageGroup
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QMessageGroup extends com.querydsl.sql.RelationalPathBase<MessageGroup> {

    public static final QMessageGroup intMessageGroup = new QMessageGroup("int_message_group");
    private static final long serialVersionUID = 284793107;
    public final NumberPath<Long> complete = createNumber("complete", Long.class);

    public final DateTimePath<java.sql.Timestamp> createdDate = createDateTime("createdDate", java.sql.Timestamp.class);

    public final StringPath groupCondition = createString("groupCondition");

    public final StringPath groupKey = createString("groupKey");

    public final NumberPath<Long> lastReleasedSequence = createNumber("lastReleasedSequence", Long.class);

    public final StringPath region = createString("region");

    public final DateTimePath<java.sql.Timestamp> updatedDate = createDateTime("updatedDate", java.sql.Timestamp.class);

    public final com.querydsl.sql.PrimaryKey<MessageGroup> intMessageGroupPk = createPrimaryKey(groupKey, region);

    public QMessageGroup(String variable) {
        super(MessageGroup.class, forVariable(variable), "test-cdewsij", "int_message_group");
        addMetadata();
    }

    public QMessageGroup(String variable, String schema, String table) {
        super(MessageGroup.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QMessageGroup(String variable, String schema) {
        super(MessageGroup.class, forVariable(variable), schema, "int_message_group");
        addMetadata();
    }

    public QMessageGroup(Path<? extends MessageGroup> path) {
        super(path.getType(), path.getMetadata(), "test-cdewsij", "int_message_group");
        addMetadata();
    }

    public QMessageGroup(PathMetadata metadata) {
        super(MessageGroup.class, metadata, "test-cdewsij", "int_message_group");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(complete, ColumnMetadata.named("complete").withIndex(4).ofType(Types.BIGINT).withSize(19));
        addMetadata(createdDate, ColumnMetadata.named("created_date").withIndex(6).ofType(Types.TIMESTAMP).withSize(29).withDigits(6).notNull());
        addMetadata(groupCondition, ColumnMetadata.named("group_condition").withIndex(3).ofType(Types.VARCHAR).withSize(255));
        addMetadata(groupKey, ColumnMetadata.named("group_key").withIndex(1).ofType(Types.CHAR).withSize(36).notNull());
        addMetadata(lastReleasedSequence, ColumnMetadata.named("last_released_sequence").withIndex(5).ofType(Types.BIGINT).withSize(19));
        addMetadata(region, ColumnMetadata.named("region").withIndex(2).ofType(Types.VARCHAR).withSize(100).notNull());
        addMetadata(updatedDate, ColumnMetadata.named("updated_date").withIndex(7).ofType(Types.TIMESTAMP).withSize(29).withDigits(6));
    }

}

