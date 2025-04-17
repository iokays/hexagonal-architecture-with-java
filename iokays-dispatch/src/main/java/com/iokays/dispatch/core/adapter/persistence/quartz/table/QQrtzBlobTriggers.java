package com.iokays.dispatch.core.adapter.persistence.quartz.table;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.SimplePath;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.sql.ColumnMetadata;

import javax.annotation.processing.Generated;
import java.sql.Types;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QQrtzBlobTriggers is a Querydsl query type for QrtzBlobTriggers
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QQrtzBlobTriggers extends com.querydsl.sql.RelationalPathBase<QrtzBlobTriggers> {

    public static final QQrtzBlobTriggers qrtzBlobTriggers = new QQrtzBlobTriggers("QRTZ_BLOB_TRIGGERS");
    private static final long serialVersionUID = -261857807;
    public final SimplePath<java.sql.Blob> blobData = createSimple("blobData", java.sql.Blob.class);

    public final StringPath schedName = createString("schedName");

    public final StringPath triggerGroup = createString("triggerGroup");

    public final StringPath triggerName = createString("triggerName");

    public QQrtzBlobTriggers(String variable) {
        super(QrtzBlobTriggers.class, forVariable(variable), "PUBLIC", "QRTZ_BLOB_TRIGGERS");
        addMetadata();
    }

    public QQrtzBlobTriggers(String variable, String schema, String table) {
        super(QrtzBlobTriggers.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QQrtzBlobTriggers(String variable, String schema) {
        super(QrtzBlobTriggers.class, forVariable(variable), schema, "QRTZ_BLOB_TRIGGERS");
        addMetadata();
    }

    public QQrtzBlobTriggers(Path<? extends QrtzBlobTriggers> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "QRTZ_BLOB_TRIGGERS");
        addMetadata();
    }

    public QQrtzBlobTriggers(PathMetadata metadata) {
        super(QrtzBlobTriggers.class, metadata, "PUBLIC", "QRTZ_BLOB_TRIGGERS");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(blobData, ColumnMetadata.named("BLOB_DATA").withIndex(4).ofType(Types.BLOB).withSize(2147483647));
        addMetadata(schedName, ColumnMetadata.named("SCHED_NAME").withIndex(1).ofType(Types.VARCHAR).withSize(120).notNull());
        addMetadata(triggerGroup, ColumnMetadata.named("TRIGGER_GROUP").withIndex(3).ofType(Types.VARCHAR).withSize(200).notNull());
        addMetadata(triggerName, ColumnMetadata.named("TRIGGER_NAME").withIndex(2).ofType(Types.VARCHAR).withSize(200).notNull());
    }

}

