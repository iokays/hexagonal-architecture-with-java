package com.iokays.authorization.core.domain.clientregistration;

import com.iokays.authorization.core.domain.clientregistration.command.CreateClientRegistration;
import com.iokays.authorization.core.domain.clientregistration.event.ClientRegistrationCreated;
import com.iokays.common.core.event.EventId;
import com.iokays.common.domain.jpa.AbstractAggregateRoot;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;

/**
 * oauth2 支持的外部授权客户信息
 */
@Entity
@Table(name = "t_client_registrations")
public class ClientRegistration extends AbstractAggregateRoot<ClientRegistration> {

    @AttributeOverride(name = "id", column = @Column(name = "client_registration_id", length = 40, nullable = false))
    private ClientRegistrationId clientRegistrationId;

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
        this.clientRegistrationId = ClientRegistrationId.makeClientRegistrationId(command.clientRegistrationType());
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

    public ClientRegistrationId clientRegistrationId() {
        return this.clientRegistrationId;
    }

    @Override
    public boolean sameIdentityAs(ClientRegistration other) {
        return false;
    }

    public ClientRegistrationInfo info() {
        return ClientRegistrationInfo.builder()
                .clientRegistrationId(this.clientRegistrationId)
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


}
