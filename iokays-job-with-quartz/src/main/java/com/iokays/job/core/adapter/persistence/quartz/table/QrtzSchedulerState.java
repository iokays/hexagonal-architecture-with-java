package com.iokays.job.core.adapter.persistence.quartz.table;

import javax.annotation.processing.Generated;

/**
 * QrtzSchedulerState is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class QrtzSchedulerState {

    private Long checkinInterval;

    private String instanceName;

    private Long lastCheckinTime;

    private String schedName;

    public Long getCheckinInterval() {
        return checkinInterval;
    }

    public void setCheckinInterval(Long checkinInterval) {
        this.checkinInterval = checkinInterval;
    }

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    public Long getLastCheckinTime() {
        return lastCheckinTime;
    }

    public void setLastCheckinTime(Long lastCheckinTime) {
        this.lastCheckinTime = lastCheckinTime;
    }

    public String getSchedName() {
        return schedName;
    }

    public void setSchedName(String schedName) {
        this.schedName = schedName;
    }

}

