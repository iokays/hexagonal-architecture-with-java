package com.iokays.dispatch.core.adapter.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class PageMessageModel implements Serializable {

    private String messageId;
    private String groupKey;
    private String content;
    private String region;
    private Long messagePriority;
    private Long messageSequence;
    private Integer status; //1: 待发送, 2:已发送

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createdDate;


}
