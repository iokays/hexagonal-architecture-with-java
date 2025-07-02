package com.iokays.dispatch.core.adapter.persistence.message.table;

import javax.annotation.processing.Generated;

/**
 * MetadataStore is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class MetadataStore {

    private String metadataKey;

    private String metadataValue;

    private String region;

    public String getMetadataKey() {
        return metadataKey;
    }

    public void setMetadataKey(String metadataKey) {
        this.metadataKey = metadataKey;
    }

    public String getMetadataValue() {
        return metadataValue;
    }

    public void setMetadataValue(String metadataValue) {
        this.metadataValue = metadataValue;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

}

