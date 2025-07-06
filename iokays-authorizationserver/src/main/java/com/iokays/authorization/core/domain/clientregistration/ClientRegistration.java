package com.iokays.authorization.core.domain.clientregistration;

import com.iokays.authorization.core.domain.clientregistration.command.CreateClientRegistration;
import com.iokays.authorization.core.domain.clientregistration.event.ClientRegistrationCreated;
import com.iokays.common.core.event.EventId;
import com.iokays.common.domain.jpa.AbstractJpaAggregateRoot;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

/**
 * oauth2 支持的外部授权客户信息
 */
@Entity
@Table(name = "t_client_registrations")
public class ClientRegistration extends AbstractJpaAggregateRoot<ClientRegistration> {

    @AttributeOverride(name = "id", column = @Column(name = "client_registration_id", length = 40, nullable = false))
    @Embedded
    private RegistrationId registrationId;

    @Enumerated(value = EnumType.STRING)
    private ClientRegistrationType clientRegistrationType;


    @Column(nullable = false)
    private String clientId;

    @Column(nullable = false)
    private String clientName;

    @Column(nullable = false)
    private String clientSecret;

    @Column(nullable = false)
    private String clientAuthenticationMethod;

    @Column(nullable = false)
    private String authorizationGrantType;

    @Column(nullable = false)
    private String authorizationUri;

    @Column(nullable = false)
    private String redirectUri;

    @Column(nullable = false)
    private Set<String> scopes;

    private String tokenUri;

    private String userInfoUri;

    private String userNameAttributeName;

    private String jwkSetUri;


    protected ClientRegistration() {
        super();
    }

    public ClientRegistration(CreateClientRegistration command) {
        this.registrationId = command.registrationId();
        this.clientRegistrationType = command.clientRegistrationType();
        this.clientId = command.clientId();
        this.clientName = command.clientName();
        this.clientSecret = command.clientSecret();
        this.clientAuthenticationMethod = command.clientAuthenticationMethod();
        this.authorizationGrantType = command.authorizationGrantType();
        this.authorizationUri = command.authorizationUri();
        this.redirectUri = command.redirectUri();
        this.scopes = command.scopes();
        this.tokenUri = command.tokenUri();
        this.userInfoUri = command.userInfoUri();
        this.userNameAttributeName = command.userNameAttributeName();
        this.jwkSetUri = command.jwkSetUri();

        this.andEvent(new ClientRegistrationCreated(EventId.generate(), this.clientName, LocalDateTime.now()));
    }

    public RegistrationId clientRegistrationId() {
        return this.registrationId;
    }

    public ClientRegistrationInfo info() {
        return ClientRegistrationInfo.builder()
                .registrationId(this.registrationId)
                .clientRegistrationType(this.clientRegistrationType)
                .clientId(this.clientId)
                .clientName(this.clientName)
                .clientSecret(this.clientSecret)
                .clientAuthenticationMethod(this.clientAuthenticationMethod)
                .authorizationGrantType(this.authorizationGrantType)
                .redirectUri(this.redirectUri)
                .scopes(Collections.unmodifiableSet(this.scopes))
                .authorizationUri(this.authorizationUri)
                .tokenUri(this.tokenUri)
                .userInfoUri(this.userInfoUri)
                .userNameAttributeName(this.userNameAttributeName)
                .jwkSetUri(this.jwkSetUri)
                .createdDate(this.getCreatedDate())
                .lastModifiedDate(this.getLastModifiedDate())
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        final ClientRegistration that = (ClientRegistration) o;
        return Objects.equals(registrationId, that.registrationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(registrationId);
    }
}
