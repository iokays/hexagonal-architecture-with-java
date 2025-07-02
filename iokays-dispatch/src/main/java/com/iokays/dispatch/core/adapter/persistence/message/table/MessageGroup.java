package com.iokays.dispatch.core.adapter.persistence.message.table;

import javax.annotation.processing.Generated;

/**
 * MessageGroup is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class MessageGroup {

    private Long complete;

    private java.sql.Timestamp createdDate;

    private String groupCondition;

    private String groupKey;

    private Long lastReleasedSequence;

    private String region;

    private java.sql.Timestamp updatedDate;

    public Long getComplete() {
        return complete;
    }

    public void setComplete(Long complete) {
        this.complete = complete;
    }

    public java.sql.Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(java.sql.Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public String getGroupCondition() {
        return groupCondition;
    }

    public void setGroupCondition(String groupCondition) {
        this.groupCondition = groupCondition;
    }

    public String getGroupKey() {
        return groupKey;
    }

    public void setGroupKey(String groupKey) {
        this.groupKey = groupKey;
    }

    public Long getLastReleasedSequence() {
        return lastReleasedSequence;
    }

    public void setLastReleasedSequence(Long lastReleasedSequence) {
        this.lastReleasedSequence = lastReleasedSequence;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public java.sql.Timestamp getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(java.sql.Timestamp updatedDate) {
        this.updatedDate = updatedDate;
    }

}

