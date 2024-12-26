package com.iokays.core.application.service.table;

import javax.annotation.processing.Generated;

/**
 * TUsers is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class TUsers {

    private Integer concurrencyVersion;

    private java.sql.Timestamp createdDate;

    private Boolean enabled;

    private Long id;

    private java.sql.Timestamp lastModifiedDate;

    private String password;

    private byte[] userId;

    private String username;

    public Integer getConcurrencyVersion() {
        return concurrencyVersion;
    }

    public void setConcurrencyVersion(Integer concurrencyVersion) {
        this.concurrencyVersion = concurrencyVersion;
    }

    public java.sql.Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(java.sql.Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getUserId() {
        return userId;
    }

    public void setUserId(byte[] userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}

