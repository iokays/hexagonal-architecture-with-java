package com.iokays.job.core.adapter.persistence.quartz.table;

import javax.annotation.processing.Generated;

/**
 * QrtzJobDetails is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class QrtzJobDetails {

    private String description;

    private Boolean isDurable;

    private Boolean isNonconcurrent;

    private Boolean isUpdateData;

    private String jobClassName;

    private java.sql.Blob jobData;

    private String jobGroup;

    private String jobName;

    private Boolean requestsRecovery;

    private String schedName;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsDurable() {
        return isDurable;
    }

    public void setIsDurable(Boolean isDurable) {
        this.isDurable = isDurable;
    }

    public Boolean getIsNonconcurrent() {
        return isNonconcurrent;
    }

    public void setIsNonconcurrent(Boolean isNonconcurrent) {
        this.isNonconcurrent = isNonconcurrent;
    }

    public Boolean getIsUpdateData() {
        return isUpdateData;
    }

    public void setIsUpdateData(Boolean isUpdateData) {
        this.isUpdateData = isUpdateData;
    }

    public String getJobClassName() {
        return jobClassName;
    }

    public void setJobClassName(String jobClassName) {
        this.jobClassName = jobClassName;
    }

    public java.sql.Blob getJobData() {
        return jobData;
    }

    public void setJobData(java.sql.Blob jobData) {
        this.jobData = jobData;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Boolean getRequestsRecovery() {
        return requestsRecovery;
    }

    public void setRequestsRecovery(Boolean requestsRecovery) {
        this.requestsRecovery = requestsRecovery;
    }

    public String getSchedName() {
        return schedName;
    }

    public void setSchedName(String schedName) {
        this.schedName = schedName;
    }

}

