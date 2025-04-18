package com.iokays.dispatch.core.adapter.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.iokays.dispatch.core.adapter.job.JobClass;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class PageMessageModel implements Serializable {

    private String messageId;
    private String messageType;
    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createdDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime lastModifiedDate;

}
