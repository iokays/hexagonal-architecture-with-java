package com.iokays.dispatch.core.adapter.persistence.quartz.table;

import javax.annotation.processing.Generated;

/**
 * QrtzLocks is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class QrtzLocks {

    private String lockName;

    private String schedName;

    public String getLockName() {
        return lockName;
    }

    public void setLockName(String lockName) {
        this.lockName = lockName;
    }

    public String getSchedName() {
        return schedName;
    }

    public void setSchedName(String schedName) {
        this.schedName = schedName;
    }

}

