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
 * QQrtzSimpropTriggers is a Querydsl query type for QrtzSimpropTriggers
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QQrtzSimpropTriggers extends com.querydsl.sql.RelationalPathBase<QrtzSimpropTriggers> {

    private static final long serialVersionUID = -894692228;

    public static final QQrtzSimpropTriggers qrtzSimpropTriggers = new QQrtzSimpropTriggers("QRTZ_SIMPROP_TRIGGERS");

    public final BooleanPath boolProp1 = createBoolean("boolProp1");

    public final BooleanPath boolProp2 = createBoolean("boolProp2");

    public final NumberPath<java.math.BigDecimal> decProp1 = createNumber("decProp1", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> decProp2 = createNumber("decProp2", java.math.BigDecimal.class);

    public final NumberPath<Integer> intProp1 = createNumber("intProp1", Integer.class);

    public final NumberPath<Integer> intProp2 = createNumber("intProp2", Integer.class);

    public final NumberPath<Long> longProp1 = createNumber("longProp1", Long.class);

    public final NumberPath<Long> longProp2 = createNumber("longProp2", Long.class);

    public final StringPath schedName = createString("schedName");

    public final StringPath strProp1 = createString("strProp1");

    public final StringPath strProp2 = createString("strProp2");

    public final StringPath strProp3 = createString("strProp3");

    public final StringPath triggerGroup = createString("triggerGroup");

    public final StringPath triggerName = createString("triggerName");

    public final com.querydsl.sql.PrimaryKey<QrtzSimpropTriggers> qrtzSimpropTriggersPk = createPrimaryKey(schedName, triggerGroup, triggerName);

    public final com.querydsl.sql.ForeignKey<QrtzTriggers> qrtzSimpropTriggersQrtzTriggersFk = createForeignKey(Arrays.asList(schedName, triggerName, triggerGroup), Arrays.asList("SCHED_NAME", "TRIGGER_NAME", "TRIGGER_GROUP"));

    public QQrtzSimpropTriggers(String variable) {
        super(QrtzSimpropTriggers.class, forVariable(variable), "PUBLIC", "QRTZ_SIMPROP_TRIGGERS");
        addMetadata();
    }

    public QQrtzSimpropTriggers(String variable, String schema, String table) {
        super(QrtzSimpropTriggers.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QQrtzSimpropTriggers(String variable, String schema) {
        super(QrtzSimpropTriggers.class, forVariable(variable), schema, "QRTZ_SIMPROP_TRIGGERS");
        addMetadata();
    }

    public QQrtzSimpropTriggers(Path<? extends QrtzSimpropTriggers> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "QRTZ_SIMPROP_TRIGGERS");
        addMetadata();
    }

    public QQrtzSimpropTriggers(PathMetadata metadata) {
        super(QrtzSimpropTriggers.class, metadata, "PUBLIC", "QRTZ_SIMPROP_TRIGGERS");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(boolProp1, ColumnMetadata.named("BOOL_PROP_1").withIndex(13).ofType(Types.BOOLEAN).withSize(1));
        addMetadata(boolProp2, ColumnMetadata.named("BOOL_PROP_2").withIndex(14).ofType(Types.BOOLEAN).withSize(1));
        addMetadata(decProp1, ColumnMetadata.named("DEC_PROP_1").withIndex(11).ofType(Types.NUMERIC).withSize(13).withDigits(4));
        addMetadata(decProp2, ColumnMetadata.named("DEC_PROP_2").withIndex(12).ofType(Types.NUMERIC).withSize(13).withDigits(4));
        addMetadata(intProp1, ColumnMetadata.named("INT_PROP_1").withIndex(7).ofType(Types.INTEGER).withSize(32));
        addMetadata(intProp2, ColumnMetadata.named("INT_PROP_2").withIndex(8).ofType(Types.INTEGER).withSize(32));
        addMetadata(longProp1, ColumnMetadata.named("LONG_PROP_1").withIndex(9).ofType(Types.BIGINT).withSize(64));
        addMetadata(longProp2, ColumnMetadata.named("LONG_PROP_2").withIndex(10).ofType(Types.BIGINT).withSize(64));
        addMetadata(schedName, ColumnMetadata.named("SCHED_NAME").withIndex(1).ofType(Types.VARCHAR).withSize(120).notNull());
        addMetadata(strProp1, ColumnMetadata.named("STR_PROP_1").withIndex(4).ofType(Types.VARCHAR).withSize(512));
        addMetadata(strProp2, ColumnMetadata.named("STR_PROP_2").withIndex(5).ofType(Types.VARCHAR).withSize(512));
        addMetadata(strProp3, ColumnMetadata.named("STR_PROP_3").withIndex(6).ofType(Types.VARCHAR).withSize(512));
        addMetadata(triggerGroup, ColumnMetadata.named("TRIGGER_GROUP").withIndex(3).ofType(Types.VARCHAR).withSize(200).notNull());
        addMetadata(triggerName, ColumnMetadata.named("TRIGGER_NAME").withIndex(2).ofType(Types.VARCHAR).withSize(200).notNull());
    }

}

