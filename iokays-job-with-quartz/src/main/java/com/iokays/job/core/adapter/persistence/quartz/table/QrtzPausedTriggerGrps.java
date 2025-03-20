package com.iokays.job.core.adapter.persistence.quartz.table;

import javax.annotation.processing.Generated;

/**
 * QrtzPausedTriggerGrps is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class QrtzPausedTriggerGrps {

    private String schedName;

    private String triggerGroup;

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

}

