package com.iokays.authorization.core.adapter.persistence.querydsl.table;

import javax.annotation.processing.Generated;

/**
 * TLocalMessage is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class TLocalMessage {

    private java.sql.Timestamp createdDate;

    private String deleted;

    private Long id;

    private java.sql.Timestamp lastModifiedDate;

    private byte[] messageBytes;

    private byte[] messageId;

    public java.sql.Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(java.sql.Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public java.sql.Timestamp getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(java.sql.Timestamp lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public byte[] getMessageBytes() {
        return messageBytes;
    }

    public void setMessageBytes(byte[] messageBytes) {
        this.messageBytes = messageBytes;
    }

    public byte[] getMessageId() {
        return messageId;
    }

    public void setMessageId(byte[] messageId) {
        this.messageId = messageId;
    }

}

