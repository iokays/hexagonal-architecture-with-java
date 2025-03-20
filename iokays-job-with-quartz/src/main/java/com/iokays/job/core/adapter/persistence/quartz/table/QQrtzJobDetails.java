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
 * QQrtzJobDetails is a Querydsl query type for QrtzJobDetails
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QQrtzJobDetails extends com.querydsl.sql.RelationalPathBase<QrtzJobDetails> {

    private static final long serialVersionUID = -2040656002;

    public static final QQrtzJobDetails qrtzJobDetails = new QQrtzJobDetails("QRTZ_JOB_DETAILS");

    public final StringPath description = createString("description");

    public final BooleanPath isDurable = createBoolean("isDurable");

    public final BooleanPath isNonconcurrent = createBoolean("isNonconcurrent");

    public final BooleanPath isUpdateData = createBoolean("isUpdateData");

    public final StringPath jobClassName = createString("jobClassName");

    public final SimplePath<java.sql.Blob> jobData = createSimple("jobData", java.sql.Blob.class);

    public final StringPath jobGroup = createString("jobGroup");

    public final StringPath jobName = createString("jobName");

    public final BooleanPath requestsRecovery = createBoolean("requestsRecovery");

    public final StringPath schedName = createString("schedName");

    public final com.querydsl.sql.PrimaryKey<QrtzJobDetails> qrtzJobDetailsPk = createPrimaryKey(jobGroup, jobName, schedName);

    public final com.querydsl.sql.ForeignKey<QrtzTriggers> _qrtzTriggersQrtzJobDetailsFk = createInvForeignKey(Arrays.asList(schedName, jobName, jobGroup), Arrays.asList("SCHED_NAME", "JOB_NAME", "JOB_GROUP"));

    public QQrtzJobDetails(String variable) {
        super(QrtzJobDetails.class, forVariable(variable), "PUBLIC", "QRTZ_JOB_DETAILS");
        addMetadata();
    }

    public QQrtzJobDetails(String variable, String schema, String table) {
        super(QrtzJobDetails.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QQrtzJobDetails(String variable, String schema) {
        super(QrtzJobDetails.class, forVariable(variable), schema, "QRTZ_JOB_DETAILS");
        addMetadata();
    }

    public QQrtzJobDetails(Path<? extends QrtzJobDetails> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "QRTZ_JOB_DETAILS");
        addMetadata();
    }

    public QQrtzJobDetails(PathMetadata metadata) {
        super(QrtzJobDetails.class, metadata, "PUBLIC", "QRTZ_JOB_DETAILS");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(description, ColumnMetadata.named("DESCRIPTION").withIndex(4).ofType(Types.VARCHAR).withSize(250));
        addMetadata(isDurable, ColumnMetadata.named("IS_DURABLE").withIndex(6).ofType(Types.BOOLEAN).withSize(1).notNull());
        addMetadata(isNonconcurrent, ColumnMetadata.named("IS_NONCONCURRENT").withIndex(7).ofType(Types.BOOLEAN).withSize(1).notNull());
        addMetadata(isUpdateData, ColumnMetadata.named("IS_UPDATE_DATA").withIndex(8).ofType(Types.BOOLEAN).withSize(1).notNull());
        addMetadata(jobClassName, ColumnMetadata.named("JOB_CLASS_NAME").withIndex(5).ofType(Types.VARCHAR).withSize(250).notNull());
        addMetadata(jobData, ColumnMetadata.named("JOB_DATA").withIndex(10).ofType(Types.BLOB).withSize(2147483647));
        addMetadata(jobGroup, ColumnMetadata.named("JOB_GROUP").withIndex(3).ofType(Types.VARCHAR).withSize(200).notNull());
        addMetadata(jobName, ColumnMetadata.named("JOB_NAME").withIndex(2).ofType(Types.VARCHAR).withSize(200).notNull());
        addMetadata(requestsRecovery, ColumnMetadata.named("REQUESTS_RECOVERY").withIndex(9).ofType(Types.BOOLEAN).withSize(1).notNull());
        addMetadata(schedName, ColumnMetadata.named("SCHED_NAME").withIndex(1).ofType(Types.VARCHAR).withSize(120).notNull());
    }

}

