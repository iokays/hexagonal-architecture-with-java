package com.iokays.job.core.adapter.persistence.quartz.table;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QQrtzCalendars is a Querydsl query type for QrtzCalendars
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QQrtzCalendars extends com.querydsl.sql.RelationalPathBase<QrtzCalendars> {

    private static final long serialVersionUID = -184241636;

    public static final QQrtzCalendars qrtzCalendars = new QQrtzCalendars("QRTZ_CALENDARS");

    public final SimplePath<java.sql.Blob> calendar = createSimple("calendar", java.sql.Blob.class);

    public final StringPath calendarName = createString("calendarName");

    public final StringPath schedName = createString("schedName");

    public final com.querydsl.sql.PrimaryKey<QrtzCalendars> qrtzCalendarsPk = createPrimaryKey(calendarName, schedName);

    public QQrtzCalendars(String variable) {
        super(QrtzCalendars.class, forVariable(variable), "PUBLIC", "QRTZ_CALENDARS");
        addMetadata();
    }

    public QQrtzCalendars(String variable, String schema, String table) {
        super(QrtzCalendars.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QQrtzCalendars(String variable, String schema) {
        super(QrtzCalendars.class, forVariable(variable), schema, "QRTZ_CALENDARS");
        addMetadata();
    }

    public QQrtzCalendars(Path<? extends QrtzCalendars> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "QRTZ_CALENDARS");
        addMetadata();
    }

    public QQrtzCalendars(PathMetadata metadata) {
        super(QrtzCalendars.class, metadata, "PUBLIC", "QRTZ_CALENDARS");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(calendar, ColumnMetadata.named("CALENDAR").withIndex(3).ofType(Types.BLOB).withSize(2147483647).notNull());
        addMetadata(calendarName, ColumnMetadata.named("CALENDAR_NAME").withIndex(2).ofType(Types.VARCHAR).withSize(200).notNull());
        addMetadata(schedName, ColumnMetadata.named("SCHED_NAME").withIndex(1).ofType(Types.VARCHAR).withSize(120).notNull());
    }

}

