package com.iokays.job.core.adapter.persistence.quartz.table;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QQrtzFiredTriggers is a Querydsl query type for QrtzFiredTriggers
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QQrtzFiredTriggers extends com.querydsl.sql.RelationalPathBase<QrtzFiredTriggers> {

    private static final long serialVersionUID = 1376723568;

    public static final QQrtzFiredTriggers qrtzFiredTriggers = new QQrtzFiredTriggers("QRTZ_FIRED_TRIGGERS");

    public final StringPath entryId = createString("entryId");

    public final NumberPath<Long> firedTime = createNumber("firedTime", Long.class);

    public final StringPath instanceName = createString("instanceName");

    public final BooleanPath isNonconcurrent = createBoolean("isNonconcurrent");

    public final StringPath jobGroup = createString("jobGroup");

    public final StringPath jobName = createString("jobName");

    public final NumberPath<Integer> priority = createNumber("priority", Integer.class);

    public final BooleanPath requestsRecovery = createBoolean("requestsRecovery");

    public final StringPath schedName = createString("schedName");

    public final NumberPath<Long> schedTime = createNumber("schedTime", Long.class);

    public final StringPath state = createString("state");

    public final StringPath triggerGroup = createString("triggerGroup");

    public final StringPath triggerName = createString("triggerName");

    public final com.querydsl.sql.PrimaryKey<QrtzFiredTriggers> qrtzFiredTriggersPk = createPrimaryKey(entryId, schedName);

    public QQrtzFiredTriggers(String variable) {
        super(QrtzFiredTriggers.class, forVariable(variable), "PUBLIC", "QRTZ_FIRED_TRIGGERS");
        addMetadata();
    }

    public QQrtzFiredTriggers(String variable, String schema, String table) {
        super(QrtzFiredTriggers.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QQrtzFiredTriggers(String variable, String schema) {
        super(QrtzFiredTriggers.class, forVariable(variable), schema, "QRTZ_FIRED_TRIGGERS");
        addMetadata();
    }

    public QQrtzFiredTriggers(Path<? extends QrtzFiredTriggers> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "QRTZ_FIRED_TRIGGERS");
        addMetadata();
    }

    public QQrtzFiredTriggers(PathMetadata metadata) {
        super(QrtzFiredTriggers.class, metadata, "PUBLIC", "QRTZ_FIRED_TRIGGERS");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(entryId, ColumnMetadata.named("ENTRY_ID").withIndex(2).ofType(Types.VARCHAR).withSize(95).notNull());
        addMetadata(firedTime, ColumnMetadata.named("FIRED_TIME").withIndex(6).ofType(Types.BIGINT).withSize(64).notNull());
        addMetadata(instanceName, ColumnMetadata.named("INSTANCE_NAME").withIndex(5).ofType(Types.VARCHAR).withSize(200).notNull());
        addMetadata(isNonconcurrent, ColumnMetadata.named("IS_NONCONCURRENT").withIndex(12).ofType(Types.BOOLEAN).withSize(1));
        addMetadata(jobGroup, ColumnMetadata.named("JOB_GROUP").withIndex(11).ofType(Types.VARCHAR).withSize(200));
        addMetadata(jobName, ColumnMetadata.named("JOB_NAME").withIndex(10).ofType(Types.VARCHAR).withSize(200));
        addMetadata(priority, ColumnMetadata.named("PRIORITY").withIndex(8).ofType(Types.INTEGER).withSize(32).notNull());
        addMetadata(requestsRecovery, ColumnMetadata.named("REQUESTS_RECOVERY").withIndex(13).ofType(Types.BOOLEAN).withSize(1));
        addMetadata(schedName, ColumnMetadata.named("SCHED_NAME").withIndex(1).ofType(Types.VARCHAR).withSize(120).notNull());
        addMetadata(schedTime, ColumnMetadata.named("SCHED_TIME").withIndex(7).ofType(Types.BIGINT).withSize(64).notNull());
        addMetadata(state, ColumnMetadata.named("STATE").withIndex(9).ofType(Types.VARCHAR).withSize(16).notNull());
        addMetadata(triggerGroup, ColumnMetadata.named("TRIGGER_GROUP").withIndex(4).ofType(Types.VARCHAR).withSize(200).notNull());
        addMetadata(triggerName, ColumnMetadata.named("TRIGGER_NAME").withIndex(3).ofType(Types.VARCHAR).withSize(200).notNull());
    }

}

