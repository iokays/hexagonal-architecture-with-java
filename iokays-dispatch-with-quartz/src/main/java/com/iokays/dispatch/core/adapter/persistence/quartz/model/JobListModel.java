package com.iokays.dispatch.core.adapter.persistence.quartz.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class JobListModel implements Serializable {

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

    private Long startTime;

    private Long endTime;

    private String cronExpression;
}
