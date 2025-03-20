package com.iokays.job.core.adapter.persistence.quartz.table;

import javax.annotation.processing.Generated;

/**
 * QrtzBlobTriggers is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class QrtzBlobTriggers {

    private java.sql.Blob blobData;

    private String schedName;

    private String triggerGroup;

    private String triggerName;

    public java.sql.Blob getBlobData() {
        return blobData;
    }

    public void setBlobData(java.sql.Blob blobData) {
        this.blobData = blobData;
    }

    public String getSchedName() {
        return schedName;
    }

    public void setSchedName(String schedName) {
        this.schedName = schedName;
    }

    public String getTriggerGroup() {
        return triggerGroup;
    }

    public void setTriggerGroup(String triggerGroup) {
        this.triggerGroup = triggerGroup;
    }

    public String getTriggerName() {
        return triggerName;
    }

    public void setTriggerName(String triggerName) {
        this.triggerName = triggerName;
    }

}

