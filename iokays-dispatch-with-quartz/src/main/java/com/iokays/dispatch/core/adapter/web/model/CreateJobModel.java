package com.iokays.dispatch.core.adapter.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.iokays.dispatch.core.adapter.job.JobClass;

import java.time.LocalDateTime;
import java.util.Map;

public record CreateJobModel(
        String name,
        String group,
        String cronExpression,
        @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8" )
        LocalDateTime startAt,
        @JsonFormat ( pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8" )
        LocalDateTime endAt,
        JobClass jobClass,
        Map<?, ?> jobData
) {}