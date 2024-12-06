package com.iokays.core.domain.clientregistration;

import com.iokays.common.core.event.EventId;
import com.iokays.common.domain.jpa.AbstractAggregateRoot;
import com.iokays.core.domain.clientregistration.command.CreateClientRegistration;
import com.iokays.core.domain.clientregistration.event.ClientRegistrationCreated;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

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
        this.clientRegistrationId = ClientRegistrationId.makeClientRegistrationId();
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

    @Override
    public boolean sameIdentityAs(ClientRegistration other) {
        return false;
    }

    public ClientRegistrationInfo info() {
        return new ClientRegistrationInfo(
                this.clientRegistrationId,
                this.clientRegistrationType,
                this.clientId,
                this.clientName,
                this.clientSecret,
                this.clientAuthenticationMethod,
                this.authorizationGrantType,
                this.redirectUri,
                this.scopes.stream().collect(Collectors.toUnmodifiableSet()),
                this.authorizationUri,
                this.tokenUri,
                this.userInfoUri,
                this.userNameAttributeName,
                this.jwkSetUri
        );
    }


}
