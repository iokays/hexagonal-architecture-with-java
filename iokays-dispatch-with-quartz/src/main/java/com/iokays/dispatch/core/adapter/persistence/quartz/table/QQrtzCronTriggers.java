package com.iokays.dispatch.core.adapter.persistence.quartz.table;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.sql.ColumnMetadata;

import javax.annotation.processing.Generated;
import java.sql.Types;
import java.util.Arrays;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QQrtzCronTriggers is a Querydsl query type for QrtzCronTriggers
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QQrtzCronTriggers extends com.querydsl.sql.RelationalPathBase<QrtzCronTriggers> {

    public static final QQrtzCronTriggers qrtzCronTriggers = new QQrtzCronTriggers("QRTZ_CRON_TRIGGERS");
    private static final long serialVersionUID = 1755801570;
    public final StringPath cronExpression = createString("cronExpression");

    public final StringPath schedName = createString("schedName");

    public final StringPath timeZoneId = createString("timeZoneId");

    public final StringPath triggerGroup = createString("triggerGroup");

    public final StringPath triggerName = createString("triggerName");

    public final com.querydsl.sql.PrimaryKey<QrtzCronTriggers> qrtzCronTriggersPk = createPrimaryKey(schedName, triggerGroup, triggerName);

    public final com.querydsl.sql.ForeignKey<QrtzTriggers> qrtzCronTriggersQrtzTriggersFk = createForeignKey(Arrays.asList(schedName, triggerName, triggerGroup), Arrays.asList("SCHED_NAME", "TRIGGER_NAME", "TRIGGER_GROUP"));

    public QQrtzCronTriggers(String variable) {
        super(QrtzCronTriggers.class, forVariable(variable), "PUBLIC", "QRTZ_CRON_TRIGGERS");
        addMetadata();
    }

    public QQrtzCronTriggers(String variable, String schema, String table) {
        super(QrtzCronTriggers.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QQrtzCronTriggers(String variable, String schema) {
        super(QrtzCronTriggers.class, forVariable(variable), schema, "QRTZ_CRON_TRIGGERS");
        addMetadata();
    }

    public QQrtzCronTriggers(Path<? extends QrtzCronTriggers> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "QRTZ_CRON_TRIGGERS");
        addMetadata();
    }

    public QQrtzCronTriggers(PathMetadata metadata) {
        super(QrtzCronTriggers.class, metadata, "PUBLIC", "QRTZ_CRON_TRIGGERS");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(cronExpression, ColumnMetadata.named("CRON_EXPRESSION").withIndex(4).ofType(Types.VARCHAR).withSize(120).notNull());
        addMetadata(schedName, ColumnMetadata.named("SCHED_NAME").withIndex(1).ofType(Types.VARCHAR).withSize(120).notNull());
        addMetadata(timeZoneId, ColumnMetadata.named("TIME_ZONE_ID").withIndex(5).ofType(Types.VARCHAR).withSize(80));
        addMetadata(triggerGroup, ColumnMetadata.named("TRIGGER_GROUP").withIndex(3).ofType(Types.VARCHAR).withSize(200).notNull());
        addMetadata(triggerName, ColumnMetadata.named("TRIGGER_NAME").withIndex(2).ofType(Types.VARCHAR).withSize(200).notNull());
    }

}

