package com.iokays.core.application.service.table;

import javax.annotation.processing.Generated;

/**
 * TOauth2AuthorizationConsent is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class TOauth2AuthorizationConsent {

    private String authorities;

    private byte[] authorizationConsentId;

    private Integer concurrencyVersion;

    private java.sql.Timestamp createdDate;

    private Long id;

    private java.sql.Timestamp lastModifiedDate;

    private String principalName;

    private byte[] registeredClientId;

    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    public byte[] getAuthorizationConsentId() {
        return authorizationConsentId;
    }

    public void setAuthorizationConsentId(byte[] authorizationConsentId) {
        this.authorizationConsentId = authorizationConsentId;
    }

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

    public String getPrincipalName() {
        return principalName;
    }

    public void setPrincipalName(String principalName) {
        this.principalName = principalName;
    }

    public byte[] getRegisteredClientId() {
        return registeredClientId;
    }

    public void setRegisteredClientId(byte[] registeredClientId) {
        this.registeredClientId = registeredClientId;
    }

}

