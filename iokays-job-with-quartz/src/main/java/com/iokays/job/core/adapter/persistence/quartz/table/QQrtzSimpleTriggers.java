package com.iokays.job.core.adapter.persistence.quartz.table;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;

import java.util.*;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QQrtzSimpleTriggers is a Querydsl query type for QrtzSimpleTriggers
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QQrtzSimpleTriggers extends com.querydsl.sql.RelationalPathBase<QrtzSimpleTriggers> {

    private static final long serialVersionUID = -714473274;

    public static final QQrtzSimpleTriggers qrtzSimpleTriggers = new QQrtzSimpleTriggers("QRTZ_SIMPLE_TRIGGERS");

    public final NumberPath<Long> repeatCount = createNumber("repeatCount", Long.class);

    public final NumberPath<Long> repeatInterval = createNumber("repeatInterval", Long.class);

    public final StringPath schedName = createString("schedName");

    public final NumberPath<Long> timesTriggered = createNumber("timesTriggered", Long.class);

    public final StringPath triggerGroup = createString("triggerGroup");

    public final StringPath triggerName = createString("triggerName");

    public final com.querydsl.sql.PrimaryKey<QrtzSimpleTriggers> qrtzSimpleTriggersPk = createPrimaryKey(schedName, triggerGroup, triggerName);

    public final com.querydsl.sql.ForeignKey<QrtzTriggers> qrtzSimpleTriggersQrtzTriggersFk = createForeignKey(Arrays.asList(schedName, triggerName, triggerGroup), Arrays.asList("SCHED_NAME", "TRIGGER_NAME", "TRIGGER_GROUP"));

    public QQrtzSimpleTriggers(String variable) {
        super(QrtzSimpleTriggers.class, forVariable(variable), "PUBLIC", "QRTZ_SIMPLE_TRIGGERS");
        addMetadata();
    }

    public QQrtzSimpleTriggers(String variable, String schema, String table) {
        super(QrtzSimpleTriggers.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QQrtzSimpleTriggers(String variable, String schema) {
        super(QrtzSimpleTriggers.class, forVariable(variable), schema, "QRTZ_SIMPLE_TRIGGERS");
        addMetadata();
    }

    public QQrtzSimpleTriggers(Path<? extends QrtzSimpleTriggers> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "QRTZ_SIMPLE_TRIGGERS");
        addMetadata();
    }

    public QQrtzSimpleTriggers(PathMetadata metadata) {
        super(QrtzSimpleTriggers.class, metadata, "PUBLIC", "QRTZ_SIMPLE_TRIGGERS");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(repeatCount, ColumnMetadata.named("REPEAT_COUNT").withIndex(4).ofType(Types.BIGINT).withSize(64).notNull());
        addMetadata(repeatInterval, ColumnMetadata.named("REPEAT_INTERVAL").withIndex(5).ofType(Types.BIGINT).withSize(64).notNull());
        addMetadata(schedName, ColumnMetadata.named("SCHED_NAME").withIndex(1).ofType(Types.VARCHAR).withSize(120).notNull());
        addMetadata(timesTriggered, ColumnMetadata.named("TIMES_TRIGGERED").withIndex(6).ofType(Types.BIGINT).withSize(64).notNull());
        addMetadata(triggerGroup, ColumnMetadata.named("TRIGGER_GROUP").withIndex(3).ofType(Types.VARCHAR).withSize(200).notNull());
        addMetadata(triggerName, ColumnMetadata.named("TRIGGER_NAME").withIndex(2).ofType(Types.VARCHAR).withSize(200).notNull());
    }

}

