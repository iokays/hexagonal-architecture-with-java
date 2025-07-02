package com.iokays.dispatch.core.adapter.persistence.message.table;

import javax.annotation.processing.Generated;

/**
 * ChannelMessage is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class ChannelMessage {

    private Long createdDate;

    private String groupKey;

    private byte[] messageBytes;

    private String messageId;

    private Long messagePriority;

    private Long messageSequence;

    private String region;

    private Integer status;

    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public String getGroupKey() {
        return groupKey;
    }

    public void setGroupKey(String groupKey) {
        this.groupKey = groupKey;
    }

    public byte[] getMessageBytes() {
        return messageBytes;
    }

    public void setMessageBytes(byte[] messageBytes) {
        this.messageBytes = messageBytes;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public Long getMessagePriority() {
        return messagePriority;
    }

    public void setMessagePriority(Long messagePriority) {
        this.messagePriority = messagePriority;
    }

    public Long getMessageSequence() {
        return messageSequence;
    }

    public void setMessageSequence(Long messageSequence) {
        this.messageSequence = messageSequence;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}

