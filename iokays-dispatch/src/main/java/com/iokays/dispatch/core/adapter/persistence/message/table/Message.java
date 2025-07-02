package com.iokays.dispatch.core.adapter.persistence.message.table;

import javax.annotation.processing.Generated;

/**
 * Message is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class Message {

    private java.sql.Timestamp createdDate;

    private byte[] messageBytes;

    private String messageId;

    private String region;

    public java.sql.Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(java.sql.Timestamp createdDate) {
        this.createdDate = createdDate;
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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

}

