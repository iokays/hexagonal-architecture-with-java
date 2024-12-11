package com.iokays.core.domain.registeredclient;

import com.iokays.common.core.event.EventId;
import com.iokays.common.domain.jpa.AbstractAggregateRoot;
import com.iokays.core.domain.registeredclient.commond.RegisterClient;
import com.iokays.core.domain.registeredclient.event.ClientRegistered;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


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
        return new RegisteredClientInfo(
                this.registeredClientId,
                this.clientId,
                this.clientIdIssuedAt,
                this.clientSecret,
                this.clientSecretExpiresAt,
                this.clientName,
                this.strToList(this.clientAuthenticationMethods),
                this.strToList(this.authorizationGrantTypes),
                this.strToList(this.redirectUris),
                this.strToList(this.postLogoutRedirectUris),
                this.strToList(this.scopes),
                this.clientSettings,
                this.tokenSettings
        );
    }

    private List<String> strToList(String str) {
        return Arrays.stream(StringUtils.defaultString(str).split(",")).toList();
    }

    private String listToStr(List<String> list) {
        return String.join(",", CollectionUtils.emptyIfNull(list));
    }

    private void setClientId(String clientId) {
        Validate.isTrue(Objects.requireNonNull(clientId).matches("^[a-zA-Z][a-zA-Z]*-[a-zA-Z]+$"), "长度为10-15字符串，含有多个字母开头与结尾, 并且最多一个-符号");
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