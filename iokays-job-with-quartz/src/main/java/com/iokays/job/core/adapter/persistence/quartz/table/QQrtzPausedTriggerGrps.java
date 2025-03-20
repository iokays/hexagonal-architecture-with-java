package com.iokays.job.core.adapter.persistence.quartz.table;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QQrtzPausedTriggerGrps is a Querydsl query type for QrtzPausedTriggerGrps
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QQrtzPausedTriggerGrps extends com.querydsl.sql.RelationalPathBase<QrtzPausedTriggerGrps> {

    private static final long serialVersionUID = 591334943;

    public static final QQrtzPausedTriggerGrps qrtzPausedTriggerGrps = new QQrtzPausedTriggerGrps("QRTZ_PAUSED_TRIGGER_GRPS");

    public final StringPath schedName = createString("schedName");

    public final StringPath triggerGroup = createString("triggerGroup");

    public final com.querydsl.sql.PrimaryKey<QrtzPausedTriggerGrps> qrtzPausedTriggerGrpsPk = createPrimaryKey(schedName, triggerGroup);

    public QQrtzPausedTriggerGrps(String variable) {
        super(QrtzPausedTriggerGrps.class, forVariable(variable), "PUBLIC", "QRTZ_PAUSED_TRIGGER_GRPS");
        addMetadata();
    }

    public QQrtzPausedTriggerGrps(String variable, String schema, String table) {
        super(QrtzPausedTriggerGrps.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QQrtzPausedTriggerGrps(String variable, String schema) {
        super(QrtzPausedTriggerGrps.class, forVariable(variable), schema, "QRTZ_PAUSED_TRIGGER_GRPS");
        addMetadata();
    }

    public QQrtzPausedTriggerGrps(Path<? extends QrtzPausedTriggerGrps> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "QRTZ_PAUSED_TRIGGER_GRPS");
        addMetadata();
    }

    public QQrtzPausedTriggerGrps(PathMetadata metadata) {
        super(QrtzPausedTriggerGrps.class, metadata, "PUBLIC", "QRTZ_PAUSED_TRIGGER_GRPS");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(schedName, ColumnMetadata.named("SCHED_NAME").withIndex(1).ofType(Types.VARCHAR).withSize(120).notNull());
        addMetadata(triggerGroup, ColumnMetadata.named("TRIGGER_GROUP").withIndex(2).ofType(Types.VARCHAR).withSize(200).notNull());
    }

}

