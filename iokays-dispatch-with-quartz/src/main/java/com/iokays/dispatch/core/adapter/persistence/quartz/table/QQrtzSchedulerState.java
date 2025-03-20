package com.iokays.dispatch.core.adapter.persistence.quartz.table;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.sql.ColumnMetadata;

import javax.annotation.processing.Generated;
import java.sql.Types;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QQrtzSchedulerState is a Querydsl query type for QrtzSchedulerState
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QQrtzSchedulerState extends com.querydsl.sql.RelationalPathBase<QrtzSchedulerState> {

    public static final QQrtzSchedulerState qrtzSchedulerState = new QQrtzSchedulerState("QRTZ_SCHEDULER_STATE");
    private static final long serialVersionUID = 1874471887;
    public final NumberPath<Long> checkinInterval = createNumber("checkinInterval", Long.class);

    public final StringPath instanceName = createString("instanceName");

    public final NumberPath<Long> lastCheckinTime = createNumber("lastCheckinTime", Long.class);

    public final StringPath schedName = createString("schedName");

    public final com.querydsl.sql.PrimaryKey<QrtzSchedulerState> qrtzSchedulerStatePk = createPrimaryKey(instanceName, schedName);

    public QQrtzSchedulerState(String variable) {
        super(QrtzSchedulerState.class, forVariable(variable), "PUBLIC", "QRTZ_SCHEDULER_STATE");
        addMetadata();
    }

    public QQrtzSchedulerState(String variable, String schema, String table) {
        super(QrtzSchedulerState.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QQrtzSchedulerState(String variable, String schema) {
        super(QrtzSchedulerState.class, forVariable(variable), schema, "QRTZ_SCHEDULER_STATE");
        addMetadata();
    }

    public QQrtzSchedulerState(Path<? extends QrtzSchedulerState> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "QRTZ_SCHEDULER_STATE");
        addMetadata();
    }

    public QQrtzSchedulerState(PathMetadata metadata) {
        super(QrtzSchedulerState.class, metadata, "PUBLIC", "QRTZ_SCHEDULER_STATE");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(checkinInterval, ColumnMetadata.named("CHECKIN_INTERVAL").withIndex(4).ofType(Types.BIGINT).withSize(64).notNull());
        addMetadata(instanceName, ColumnMetadata.named("INSTANCE_NAME").withIndex(2).ofType(Types.VARCHAR).withSize(200).notNull());
        addMetadata(lastCheckinTime, ColumnMetadata.named("LAST_CHECKIN_TIME").withIndex(3).ofType(Types.BIGINT).withSize(64).notNull());
        addMetadata(schedName, ColumnMetadata.named("SCHED_NAME").withIndex(1).ofType(Types.VARCHAR).withSize(120).notNull());
    }

}

