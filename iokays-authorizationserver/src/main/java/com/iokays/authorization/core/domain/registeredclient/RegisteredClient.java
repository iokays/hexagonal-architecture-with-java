package com.iokays.authorization.core.domain.registeredclient;

import com.iokays.authorization.core.domain.registeredclient.commond.RegisterClient;
import com.iokays.authorization.core.domain.registeredclient.event.ClientRegistered;
import com.iokays.common.core.event.EventId;
import com.iokays.common.domain.jpa.AbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;


@Entity
@Table(name = "t_oauth2_registered_client")
public class RegisteredClient extends AbstractAggregateRoot<RegisteredClient> {

    @Column(length = 1000, unique = true, nullable = false)
    private RegisteredClientId registeredClientId;

    @Column(length = 1000, unique = true, nullable = false)
    private String clientId;

    @Column(nullable = false)
    private Instant clientIdIssuedAt;

    @Column(nullable = false)
    private String clientSecret;

    @Column(nullable = false)
    private Instant clientSecretExpiresAt;

    @Column(nullable = false)
    private String clientName;

    @Column(length = 1000, nullable = false)
    private String clientAuthenticationMethods;

    @Column(length = 1000, nullable = false)
    private String authorizationGrantTypes;

    @Column(length = 1000, nullable = false)
    private String redirectUris;

    @Column(length = 1000, nullable = false)
    private String postLogoutRedirectUris;

    @Column(length = 1000, nullable = false)
    private String scopes;

    @Column(length = 2000, nullable = false)
    private String clientSettings;

    @Column(length = 2000, nullable = false)
    private String tokenSettings;

    protected RegisteredClient() {
        super();
    }

    public static RegisteredClient make(RegisterClient registerClient) {
        final RegisteredClient registeredClient = new RegisteredClient();
        registeredClient.setRegisteredClientId(RegisteredClientId.makeRegisteredClientId());
        registeredClient.setClientId(registerClient.clientId());
        registeredClient.setClientIdIssuedAt(registerClient.clientIdIssuedAt());
        registeredClient.setClientSecret(registerClient.clientSecret());
        registeredClient.setClientSecretExpiresAt(registerClient.clientSecretExpiresAt());
        registeredClient.setClientName(registerClient.clientName());
        registeredClient.setClientAuthenticationMethods(registerClient.clientAuthenticationMethods());
        registeredClient.setAuthorizationGrantTypes(registerClient.authorizationGrantTypes());
        registeredClient.setRedirectUris(registerClient.redirectUris());
        registeredClient.setPostLogoutRedirectUris(registerClient.postLogoutRedirectUris());
        registeredClient.setScopes(registerClient.scopes());
        registeredClient.setClientSettings(registerClient.clientSettings());
        registeredClient.setTokenSettings(registerClient.tokenSettings());

        // 事件
        registeredClient.registerEvent(new ClientRegistered(EventId.generate(), registeredClient.registeredClientId, registeredClient.clientId, registeredClient.clientName, registeredClient.clientIdIssuedAt));

        return registeredClient;
    }

    public RegisteredClientId registeredClientId() {
        return this.registeredClientId;
    }

    public RegisteredClientInfo info() {
        return RegisteredClientInfo.builder()
                .registeredClientId(this.registeredClientId)
                .clientId(this.clientId)
                .clientIdIssuedAt(this.clientIdIssuedAt)
                .clientSecret(this.clientSecret)
                .clientSecretExpiresAt(this.clientSecretExpiresAt)
                .clientName(this.clientName)
                .clientAuthenticationMethods(this.strToList(this.clientAuthenticationMethods))
                .authorizationGrantTypes(this.strToList(this.authorizationGrantTypes))
                .redirectUris(this.strToList(this.redirectUris))
                .postLogoutRedirectUris(this.strToList(this.postLogoutRedirectUris))
                .scopes(this.strToList(this.scopes))
                .clientSettings(this.clientSettings)
                .tokenSettings(this.tokenSettings)
                .createdDate(this.getCreatedDate())
                .lastModifiedDate(this.getLastModifiedDate())
                .build();
    }

    private List<String> strToList(String str) {
        return Arrays.stream(StringUtils.defaultString(str).split(",")).toList();
    }

    private String listToStr(List<String> list) {
        return String.join(",", CollectionUtils.emptyIfNull(list));
    }

    private void setClientId(String clientId) {
        Validate.notNull(clientId);
        this.clientId = clientId;
    }

    private void setRegisteredClientId(RegisteredClientId registeredClientId) {
        this.registeredClientId = registeredClientId;
    }

    private void setClientIdIssuedAt(Instant clientIdIssuedAt) {
        this.clientIdIssuedAt = clientIdIssuedAt;
    }

    private void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    private void setClientSecretExpiresAt(Instant clientSecretExpiresAt) {
        this.clientSecretExpiresAt = clientSecretExpiresAt;
    }

    private void setClientName(String clientName) {
        this.clientName = clientName;
    }

    private void setClientAuthenticationMethods(List<String> clientAuthenticationMethods) {
        this.clientAuthenticationMethods = listToStr(clientAuthenticationMethods);
    }

    private void setAuthorizationGrantTypes(List<String> authorizationGrantTypes) {
        this.authorizationGrantTypes = this.listToStr(authorizationGrantTypes);
    }

    private void setRedirectUris(List<String> redirectUris) {
        this.redirectUris = this.listToStr(redirectUris);
    }

    private void setPostLogoutRedirectUris(List<String> postLogoutRedirectUris) {
        this.postLogoutRedirectUris = this.listToStr(postLogoutRedirectUris);
    }

    private void setScopes(List<String> scopes) {
        this.scopes = this.listToStr(scopes);
    }

    private void setClientSettings(String clientSettings) {
        this.clientSettings = clientSettings;
    }

    private void setTokenSettings(String tokenSettings) {
        this.tokenSettings = tokenSettings;
    }

    @Override
    public boolean sameIdentityAs(RegisteredClient other) {
        return this.clientId.equals(other.clientId);
    }

}