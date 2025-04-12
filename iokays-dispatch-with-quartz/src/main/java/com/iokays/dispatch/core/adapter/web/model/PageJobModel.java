package com.iokays.dispatch.core.adapter.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.iokays.dispatch.core.adapter.job.JobClass;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
public class PageJobModel implements Serializable {

    private String description;

    private Boolean isDurable;

    private Boolean isNonconcurrent;

    private Boolean isUpdateData;

    private JobClass jobClassName;

    private java.sql.Blob jobData;

    private String jobGroup;

    private String jobName;

    private Boolean requestsRecovery;

    private String schedName;

    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8" )
    private LocalDateTime startTime;

    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8" )
    private LocalDateTime endTime;

    private String cronExpression;

    private Set<String> actions;
}
