package com.iokays.authorization.core.domain.authorizationconsent;

import com.iokays.authorization.core.domain.authorizationconsent.command.SaveAuthorizationConsent;
import com.iokays.authorization.core.domain.registeredclient.RegisteredClientId;
import com.iokays.common.domain.jpa.AbstractJpaAggregateRoot;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "t_oauth2_authorization_consent", uniqueConstraints = @UniqueConstraint(columnNames = {"registered_client_id", "principal_name"}))
public class AuthorizationConsent extends AbstractJpaAggregateRoot<AuthorizationConsent> {

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "authorization_consent_id", unique = true, length = 40, nullable = false))
    private AuthorizationConsentId authorizationConsentId;

    @Embedded
    @Column(nullable = false)
    @AttributeOverride(name = "id", column = @Column(name = "registered_client_id", length = 40, unique = true, nullable = false))
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
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        final AuthorizationConsent that = (AuthorizationConsent) o;
        return Objects.equals(authorizationConsentId, that.authorizationConsentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorizationConsentId);
    }
}