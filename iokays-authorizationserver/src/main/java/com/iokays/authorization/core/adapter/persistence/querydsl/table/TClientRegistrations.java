package com.iokays.authorization.core.adapter.persistence.querydsl.table;

import javax.annotation.processing.Generated;

/**
 * TClientRegistrations is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class TClientRegistrations {

    private String authorizationGrantType;

    private String authorizationUri;

    private String clientAuthenticationMethod;

    private String clientId;

    private String clientName;

    private byte[] clientRegistrationId;

    private Object clientRegistrationType;

    private String clientSecret;

    private Integer concurrencyVersion;

    private java.sql.Timestamp createdDate;

    private Long id;

    private String jwkSetUri;

    private java.sql.Timestamp lastModifiedDate;

    private String redirectUri;

    private Object[] scopes;

    private String tokenUri;

    private String userInfoUri;

    private String userNameAttributeName;

    public String getAuthorizationGrantType() {
        return authorizationGrantType;
    }

    public void setAuthorizationGrantType(String authorizationGrantType) {
        this.authorizationGrantType = authorizationGrantType;
    }

    public String getAuthorizationUri() {
        return authorizationUri;
    }

    public void setAuthorizationUri(String authorizationUri) {
        this.authorizationUri = authorizationUri;
    }

    public String getClientAuthenticationMethod() {
        return clientAuthenticationMethod;
    }

    public void setClientAuthenticationMethod(String clientAuthenticationMethod) {
        this.clientAuthenticationMethod = clientAuthenticationMethod;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public byte[] getClientRegistrationId() {
        return clientRegistrationId;
    }

    public void setClientRegistrationId(byte[] clientRegistrationId) {
        this.clientRegistrationId = clientRegistrationId;
    }

    public Object getClientRegistrationType() {
        return clientRegistrationType;
    }

    public void setClientRegistrationType(Object clientRegistrationType) {
        this.clientRegistrationType = clientRegistrationType;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
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

    public String getJwkSetUri() {
        return jwkSetUri;
    }

    public void setJwkSetUri(String jwkSetUri) {
        this.jwkSetUri = jwkSetUri;
    }

    public java.sql.Timestamp getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(java.sql.Timestamp lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public Object[] getScopes() {
        return scopes;
    }

    public void setScopes(Object[] scopes) {
        this.scopes = scopes;
    }

    public String getTokenUri() {
        return tokenUri;
    }

    public void setTokenUri(String tokenUri) {
        this.tokenUri = tokenUri;
    }

    public String getUserInfoUri() {
        return userInfoUri;
    }

    public void setUserInfoUri(String userInfoUri) {
        this.userInfoUri = userInfoUri;
    }

    public String getUserNameAttributeName() {
        return userNameAttributeName;
    }

    public void setUserNameAttributeName(String userNameAttributeName) {
        this.userNameAttributeName = userNameAttributeName;
    }

}

