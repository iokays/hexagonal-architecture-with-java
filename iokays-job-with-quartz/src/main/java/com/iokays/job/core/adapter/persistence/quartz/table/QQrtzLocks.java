package com.iokays.job.core.adapter.persistence.quartz.table;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QQrtzLocks is a Querydsl query type for QrtzLocks
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QQrtzLocks extends com.querydsl.sql.RelationalPathBase<QrtzLocks> {

    private static final long serialVersionUID = 980472143;

    public static final QQrtzLocks qrtzLocks = new QQrtzLocks("QRTZ_LOCKS");

    public final StringPath lockName = createString("lockName");

    public final StringPath schedName = createString("schedName");

    public final com.querydsl.sql.PrimaryKey<QrtzLocks> qrtzLocksPk = createPrimaryKey(lockName, schedName);

    public QQrtzLocks(String variable) {
        super(QrtzLocks.class, forVariable(variable), "PUBLIC", "QRTZ_LOCKS");
        addMetadata();
    }

    public QQrtzLocks(String variable, String schema, String table) {
        super(QrtzLocks.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QQrtzLocks(String variable, String schema) {
        super(QrtzLocks.class, forVariable(variable), schema, "QRTZ_LOCKS");
        addMetadata();
    }

    public QQrtzLocks(Path<? extends QrtzLocks> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "QRTZ_LOCKS");
        addMetadata();
    }

    public QQrtzLocks(PathMetadata metadata) {
        super(QrtzLocks.class, metadata, "PUBLIC", "QRTZ_LOCKS");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(lockName, ColumnMetadata.named("LOCK_NAME").withIndex(2).ofType(Types.VARCHAR).withSize(40).notNull());
        addMetadata(schedName, ColumnMetadata.named("SCHED_NAME").withIndex(1).ofType(Types.VARCHAR).withSize(120).notNull());
    }

}

