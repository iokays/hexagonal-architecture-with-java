package com.iokays.dispatch.core.adapter.persistence.quartz.table;

import javax.annotation.processing.Generated;

/**
 * QrtzSimpleTriggers is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class QrtzSimpleTriggers {

    private Long repeatCount;

    private Long repeatInterval;

    private String schedName;

    private Long timesTriggered;

    private String triggerGroup;

    private String triggerName;

    public Long getRepeatCount() {
        return repeatCount;
    }

    public void setRepeatCount(Long repeatCount) {
        this.repeatCount = repeatCount;
    }

    public Long getRepeatInterval() {
        return repeatInterval;
    }

    public void setRepeatInterval(Long repeatInterval) {
        this.repeatInterval = repeatInterval;
    }

    public String getSchedName() {
        return schedName;
    }

    public void setSchedName(String schedName) {
        this.schedName = schedName;
    }

    public Long getTimesTriggered() {
        return timesTriggered;
    }

    public void setTimesTriggered(Long timesTriggered) {
        this.timesTriggered = timesTriggered;
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

