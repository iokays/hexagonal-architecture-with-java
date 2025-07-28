package com.iokays.authorization.core.domain.authorization;

import com.iokays.authorization.core.domain.authorization.command.SaveAuthorization;
import com.iokays.authorization.core.domain.authorization.event.AuthorizationCreated;
import com.iokays.authorization.core.domain.authorization.event.AuthorizationUpdated;
import com.iokays.authorization.core.domain.registeredclient.RegisteredClientCode;
import com.iokays.common.core.event.EventId;
import com.iokays.common.domain.jpa.AbstractJpaAggregateRoot;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.apache.commons.lang3.StringUtils;

import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "t_oauth2_authorization")
public class Authorization extends AbstractJpaAggregateRoot<Authorization> {

    @AttributeOverride(name = "code", column = @Column(name = "authorization_code", unique = true, length = 40, nullable = false))
    private AuthorizationCode authorizationCode;

    @AttributeOverride(name = "code", column = @Column(name = "registeredClient_code", length = 40, nullable = false))
    private RegisteredClientCode registeredClientCode;

    @Column(nullable = false)
    private String principalName;

    @Column(nullable = false)
    private String authorizationGrantType;

    @Column(length = 1000, nullable = false)
    private String authorizedScopes;
    @Column(length = 40000, nullable = false)
    private String attributes;
    @Column(length = 500, nullable = false)
    private String state;

    @Column(length = 4000)
    private String authorizationCodeValue;
    private Instant authorizationCodeIssuedAt;
    private Instant authorizationCodeExpiresAt;
    private String authorizationCodeMetadata;

    @Column(length = 4000)
    private String accessTokenValue;
    private Instant accessTokenIssuedAt;
    private Instant accessTokenExpiresAt;
    @Column(length = 2000)
    private String accessTokenMetadata;
    private String accessTokenType;
    @Column(length = 1000)
    private String accessTokenScopes;

    @Column(length = 4000)
    private String refreshTokenValue;
    private Instant refreshTokenIssuedAt;
    private Instant refreshTokenExpiresAt;
    @Column(length = 2000)
    private String refreshTokenMetadata;

    @Column(length = 4000)
    private String oidcIdTokenValue;
    private Instant oidcIdTokenIssuedAt;
    private Instant oidcIdTokenExpiresAt;
    @Column(length = 2000)
    private String oidcIdTokenMetadata;
    @Column(length = 2000)
    private String oidcIdTokenClaims;

    @Column(length = 4000)
    private String userCodeValue;
    private Instant userCodeIssuedAt;
    private Instant userCodeExpiresAt;
    @Column(length = 2000)
    private String userCodeMetadata;

    @Column(length = 4000)
    private String deviceCodeValue;
    private Instant deviceCodeIssuedAt;
    private Instant deviceCodeExpiresAt;
    @Column(length = 2000)
    private String deviceCodeMetadata;

    protected Authorization() {
        super();
    }

    public Authorization(SaveAuthorization command) {
        this();
        this.set(command);
        this.registerEvent(AuthorizationCreated.builder().eventId(EventId.generate()).authorizationId(this.authorizationCode).createdAt(Instant.now()).build());
    }

    public void update(SaveAuthorization command) {
        this.set(command);
        this.registerEvent(AuthorizationUpdated.builder().eventId(EventId.generate()).authorizationId(this.authorizationCode).createdAt(Instant.now()).build());
    }

    private void set(SaveAuthorization command) {
        this.authorizationCode = command.authorizationId();
        this.registeredClientCode = command.registeredClientId();
        this.principalName = command.principalName();
        this.authorizationGrantType = command.authorizationGrantType();
        this.authorizedScopes = command.authorizedScopes();
        this.attributes = command.attributes();
        this.state = StringUtils.defaultString(command.state());

        this.authorizationCodeValue = command.authorizationCodeValue();
        this.authorizationCodeIssuedAt = command.authorizationCodeIssuedAt();
        this.authorizationCodeExpiresAt = command.authorizationCodeExpiresAt();
        this.authorizationCodeMetadata = command.authorizationCodeMetadata();

        this.accessTokenValue = command.accessTokenValue();
        this.accessTokenIssuedAt = command.accessTokenIssuedAt();
        this.accessTokenExpiresAt = command.accessTokenExpiresAt();
        this.accessTokenMetadata = command.accessTokenMetadata();
        this.accessTokenType = command.accessTokenType();
        this.accessTokenScopes = command.accessTokenScopes();

        this.refreshTokenValue = command.refreshTokenValue();
        this.refreshTokenIssuedAt = command.refreshTokenIssuedAt();
        this.refreshTokenExpiresAt = command.refreshTokenExpiresAt();
        this.refreshTokenMetadata = command.refreshTokenMetadata();

        this.oidcIdTokenValue = command.oidcIdTokenValue();
        this.oidcIdTokenIssuedAt = command.oidcIdTokenIssuedAt();
        this.oidcIdTokenExpiresAt = command.oidcIdTokenExpiresAt();
        this.oidcIdTokenMetadata = command.oidcIdTokenMetadata();
        this.oidcIdTokenClaims = command.oidcIdTokenClaims();

        this.userCodeValue = command.userCodeValue();
        this.userCodeIssuedAt = command.userCodeIssuedAt();
        this.userCodeExpiresAt = command.userCodeExpiresAt();
        this.userCodeMetadata = command.userCodeMetadata();

        this.deviceCodeValue = command.deviceCodeValue();
        this.deviceCodeIssuedAt = command.deviceCodeIssuedAt();
        this.deviceCodeExpiresAt = command.deviceCodeExpiresAt();
        this.deviceCodeMetadata = command.deviceCodeMetadata();

    }


    public AuthorizationInfo info() {
        return AuthorizationInfo.builder()
                .authorizationId(this.authorizationCode)
                .registeredClientId(this.registeredClientCode)
                .principalName(this.principalName)
                .authorizationGrantType(this.authorizationGrantType)
                .authorizedScopes(this.authorizedScopes)
                .attributes(this.attributes)
                .state(this.state)
                .authorizationCodeValue(this.authorizationCodeValue)
                .authorizationCodeIssuedAt(this.authorizationCodeIssuedAt)
                .authorizationCodeExpiresAt(this.authorizationCodeExpiresAt)
                .authorizationCodeMetadata(this.authorizationCodeMetadata)
                .accessTokenValue(this.accessTokenValue)
                .accessTokenIssuedAt(this.accessTokenIssuedAt)
                .accessTokenExpiresAt(this.accessTokenExpiresAt)
                .accessTokenMetadata(this.accessTokenMetadata)
                .accessTokenType(this.accessTokenType)
                .accessTokenScopes(this.accessTokenScopes)
                .refreshTokenValue(this.refreshTokenValue)
                .refreshTokenIssuedAt(this.refreshTokenIssuedAt)
                .refreshTokenExpiresAt(this.refreshTokenExpiresAt)
                .refreshTokenMetadata(this.refreshTokenMetadata)
                .oidcIdTokenValue(this.oidcIdTokenValue)
                .oidcIdTokenIssuedAt(this.oidcIdTokenIssuedAt)
                .oidcIdTokenExpiresAt(this.oidcIdTokenExpiresAt)
                .oidcIdTokenMetadata(this.oidcIdTokenMetadata)
                .oidcIdTokenClaims(this.oidcIdTokenClaims)
                .userCodeValue(this.userCodeValue)
                .userCodeIssuedAt(this.userCodeIssuedAt)
                .userCodeExpiresAt(this.userCodeExpiresAt)
                .userCodeMetadata(this.userCodeMetadata)
                .deviceCodeValue(this.deviceCodeValue)
                .deviceCodeIssuedAt(this.deviceCodeIssuedAt)
                .deviceCodeExpiresAt(this.deviceCodeExpiresAt)
                .deviceCodeMetadata(this.deviceCodeMetadata)
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        final Authorization that = (Authorization) o;
        return Objects.equals(authorizationCode, that.authorizationCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorizationCode, registeredClientCode);
    }
}