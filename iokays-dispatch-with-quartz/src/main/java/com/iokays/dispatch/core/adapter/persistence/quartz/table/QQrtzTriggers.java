package com.iokays.dispatch.core.adapter.persistence.quartz.table;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.SimplePath;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.sql.ColumnMetadata;

import javax.annotation.processing.Generated;
import java.sql.Types;
import java.util.Arrays;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QQrtzTriggers is a Querydsl query type for QrtzTriggers
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QQrtzTriggers extends com.querydsl.sql.RelationalPathBase<QrtzTriggers> {

    public static final QQrtzTriggers qrtzTriggers = new QQrtzTriggers("QRTZ_TRIGGERS");
    private static final long serialVersionUID = -1234019404;
    public final StringPath calendarName = createString("calendarName");

    public final StringPath description = createString("description");

    public final NumberPath<Long> endTime = createNumber("endTime", Long.class);

    public final SimplePath<java.sql.Blob> jobData = createSimple("jobData", java.sql.Blob.class);

    public final StringPath jobGroup = createString("jobGroup");

    public final StringPath jobName = createString("jobName");

    public final NumberPath<Short> misfireInstr = createNumber("misfireInstr", Short.class);

    public final NumberPath<Long> nextFireTime = createNumber("nextFireTime", Long.class);

    public final NumberPath<Long> prevFireTime = createNumber("prevFireTime", Long.class);

    public final NumberPath<Integer> priority = createNumber("priority", Integer.class);

    public final StringPath schedName = createString("schedName");

    public final NumberPath<Long> startTime = createNumber("startTime", Long.class);

    public final StringPath triggerGroup = createString("triggerGroup");

    public final StringPath triggerName = createString("triggerName");

    public final StringPath triggerState = createString("triggerState");

    public final StringPath triggerType = createString("triggerType");

    public final com.querydsl.sql.PrimaryKey<QrtzTriggers> qrtzTriggersPk = createPrimaryKey(schedName, triggerGroup, triggerName);

    public final com.querydsl.sql.ForeignKey<QrtzJobDetails> qrtzTriggersQrtzJobDetailsFk = createForeignKey(Arrays.asList(schedName, jobName, jobGroup), Arrays.asList("SCHED_NAME", "JOB_NAME", "JOB_GROUP"));

    public final com.querydsl.sql.ForeignKey<QrtzCronTriggers> _qrtzCronTriggersQrtzTriggersFk = createInvForeignKey(Arrays.asList(schedName, triggerName, triggerGroup), Arrays.asList("SCHED_NAME", "TRIGGER_NAME", "TRIGGER_GROUP"));

    public final com.querydsl.sql.ForeignKey<QrtzSimpleTriggers> _qrtzSimpleTriggersQrtzTriggersFk = createInvForeignKey(Arrays.asList(schedName, triggerName, triggerGroup), Arrays.asList("SCHED_NAME", "TRIGGER_NAME", "TRIGGER_GROUP"));

    public final com.querydsl.sql.ForeignKey<QrtzSimpropTriggers> _qrtzSimpropTriggersQrtzTriggersFk = createInvForeignKey(Arrays.asList(schedName, triggerName, triggerGroup), Arrays.asList("SCHED_NAME", "TRIGGER_NAME", "TRIGGER_GROUP"));

    public QQrtzTriggers(String variable) {
        super(QrtzTriggers.class, forVariable(variable), "PUBLIC", "QRTZ_TRIGGERS");
        addMetadata();
    }

    public QQrtzTriggers(String variable, String schema, String table) {
        super(QrtzTriggers.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QQrtzTriggers(String variable, String schema) {
        super(QrtzTriggers.class, forVariable(variable), schema, "QRTZ_TRIGGERS");
        addMetadata();
    }

    public QQrtzTriggers(Path<? extends QrtzTriggers> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "QRTZ_TRIGGERS");
        addMetadata();
    }

    public QQrtzTriggers(PathMetadata metadata) {
        super(QrtzTriggers.class, metadata, "PUBLIC", "QRTZ_TRIGGERS");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(calendarName, ColumnMetadata.named("CALENDAR_NAME").withIndex(14).ofType(Types.VARCHAR).withSize(200));
        addMetadata(description, ColumnMetadata.named("DESCRIPTION").withIndex(6).ofType(Types.VARCHAR).withSize(250));
        addMetadata(endTime, ColumnMetadata.named("END_TIME").withIndex(13).ofType(Types.BIGINT).withSize(64));
        addMetadata(jobData, ColumnMetadata.named("JOB_DATA").withIndex(16).ofType(Types.BLOB).withSize(2147483647));
        addMetadata(jobGroup, ColumnMetadata.named("JOB_GROUP").withIndex(5).ofType(Types.VARCHAR).withSize(200).notNull());
        addMetadata(jobName, ColumnMetadata.named("JOB_NAME").withIndex(4).ofType(Types.VARCHAR).withSize(200).notNull());
        addMetadata(misfireInstr, ColumnMetadata.named("MISFIRE_INSTR").withIndex(15).ofType(Types.SMALLINT).withSize(16));
        addMetadata(nextFireTime, ColumnMetadata.named("NEXT_FIRE_TIME").withIndex(7).ofType(Types.BIGINT).withSize(64));
        addMetadata(prevFireTime, ColumnMetadata.named("PREV_FIRE_TIME").withIndex(8).ofType(Types.BIGINT).withSize(64));
        addMetadata(priority, ColumnMetadata.named("PRIORITY").withIndex(9).ofType(Types.INTEGER).withSize(32));
        addMetadata(schedName, ColumnMetadata.named("SCHED_NAME").withIndex(1).ofType(Types.VARCHAR).withSize(120).notNull());
        addMetadata(startTime, ColumnMetadata.named("START_TIME").withIndex(12).ofType(Types.BIGINT).withSize(64).notNull());
        addMetadata(triggerGroup, ColumnMetadata.named("TRIGGER_GROUP").withIndex(3).ofType(Types.VARCHAR).withSize(200).notNull());
        addMetadata(triggerName, ColumnMetadata.named("TRIGGER_NAME").withIndex(2).ofType(Types.VARCHAR).withSize(200).notNull());
        addMetadata(triggerState, ColumnMetadata.named("TRIGGER_STATE").withIndex(10).ofType(Types.VARCHAR).withSize(16).notNull());
        addMetadata(triggerType, ColumnMetadata.named("TRIGGER_TYPE").withIndex(11).ofType(Types.VARCHAR).withSize(8).notNull());
    }

}

