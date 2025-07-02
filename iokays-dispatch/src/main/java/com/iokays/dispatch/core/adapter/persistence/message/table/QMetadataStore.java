package com.iokays.dispatch.core.adapter.persistence.message.table;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.sql.ColumnMetadata;

import javax.annotation.processing.Generated;
import java.sql.Types;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QMetadataStore is a Querydsl query type for MetadataStore
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QMetadataStore extends com.querydsl.sql.RelationalPathBase<MetadataStore> {

    public static final QMetadataStore intMetadataStore = new QMetadataStore("int_metadata_store");
    private static final long serialVersionUID = 128145399;
    public final StringPath metadataKey = createString("metadataKey");

    public final StringPath metadataValue = createString("metadataValue");

    public final StringPath region = createString("region");

    public final com.querydsl.sql.PrimaryKey<MetadataStore> intMetadataStorePk = createPrimaryKey(metadataKey, region);

    public QMetadataStore(String variable) {
        super(MetadataStore.class, forVariable(variable), "test-cdewsij", "int_metadata_store");
        addMetadata();
    }

    public QMetadataStore(String variable, String schema, String table) {
        super(MetadataStore.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QMetadataStore(String variable, String schema) {
        super(MetadataStore.class, forVariable(variable), schema, "int_metadata_store");
        addMetadata();
    }

    public QMetadataStore(Path<? extends MetadataStore> path) {
        super(path.getType(), path.getMetadata(), "test-cdewsij", "int_metadata_store");
        addMetadata();
    }

    public QMetadataStore(PathMetadata metadata) {
        super(MetadataStore.class, metadata, "test-cdewsij", "int_metadata_store");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(metadataKey, ColumnMetadata.named("metadata_key").withIndex(1).ofType(Types.VARCHAR).withSize(255).notNull());
        addMetadata(metadataValue, ColumnMetadata.named("metadata_value").withIndex(2).ofType(Types.VARCHAR).withSize(4000));
        addMetadata(region, ColumnMetadata.named("region").withIndex(3).ofType(Types.VARCHAR).withSize(100).notNull());
    }

}

