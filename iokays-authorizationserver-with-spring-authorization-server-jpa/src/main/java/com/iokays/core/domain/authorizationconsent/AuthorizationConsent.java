package com.iokays.core.domain.authorizationconsent;

import com.iokays.common.domain.jpa.AbstractAggregateRoot;
import com.iokays.core.domain.authorizationconsent.command.SaveAuthorizationConsent;
import com.iokays.core.domain.registeredclient.RegisteredClientId;
import jakarta.persistence.*;

@Entity
@Table(name = "t_oauth2_authorization_consent", uniqueConstraints = @UniqueConstraint(columnNames = {"registered_client_id", "principal_name"}))
public class AuthorizationConsent extends AbstractAggregateRoot<AuthorizationConsent> {

    @AttributeOverride(name = "id", column = @Column(name = "authorization_consent_id", unique = true, length = 40, nullable = false))
    private AuthorizationConsentId authorizationConsentId;

    @Column(nullable = false)
    private RegisteredClientId registeredClientId;

    @Column(nullable = false)
    private String principalName;

    @Column(length = 1000, nullable = false)
    private String authorities;

    protected AuthorizationConsent() {
        super();
    }

    public AuthorizationConsent(SaveAuthorizationConsent command) {
        this();
        this.authorizationConsentId = AuthorizationConsentId.makeAuthorizationConsentId(command.registeredClientId(), command.principalName());
        this.registeredClientId = command.registeredClientId();
        this.principalName = command.principalName();
        this.setAuthorities(command.authorities());
    }

    public void updateAuthorities(String authorities) {
        this.setAuthorities(authorities);
    }

    private void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    public AuthorizationConsentInfo info() {
        return AuthorizationConsentInfo.builder()
                .authorizationConsentId(this.authorizationConsentId)
                .registeredClientId(this.registeredClientId)
                .principalName(this.principalName)
                .authorities(this.authorities)
                .build();
    }

    @Override
    public boolean sameIdentityAs(AuthorizationConsent other) {
        return this.authorizationConsentId.equals(other.authorizationConsentId);
    }
}