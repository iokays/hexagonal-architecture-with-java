package com.iokays.authorization.core.domain.authorizationconsent;

import com.iokays.authorization.core.domain.authorizationconsent.command.SaveAuthorizationConsent;
import com.iokays.authorization.core.domain.registeredclient.RegisteredClientCode;
import com.iokays.common.domain.jpa.AbstractJpaAggregateRoot;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "t_oauth2_authorization_consent", uniqueConstraints = @UniqueConstraint(columnNames = {"registered_client_id", "principal_name"}))
public class AuthorizationConsent extends AbstractJpaAggregateRoot<AuthorizationConsent> {

    @Embedded
    @AttributeOverride(name = "code", column = @Column(name = "authorization_consent_", unique = true, length = 40, nullable = false))
    private AuthorizationConsentCode authorizationConsentCode;

    @Embedded
    @Column(nullable = false)
    @AttributeOverride(name = "code", column = @Column(name = "registered_client_code", length = 40, unique = true, nullable = false))
    private RegisteredClientCode registeredClientCode;

    @Column(nullable = false)
    private String principalName;

    @Column(length = 1000, nullable = false)
    private String authorities;

    protected AuthorizationConsent() {
        super();
    }

    public AuthorizationConsent(SaveAuthorizationConsent command) {
        this();
        this.authorizationConsentCode = AuthorizationConsentCode.makeAuthorizationConsentId(command.registeredClientId(), command.principalName());
        this.registeredClientCode = command.registeredClientId();
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
                .authorizationConsentId(this.authorizationConsentCode)
                .registeredClientId(this.registeredClientCode)
                .principalName(this.principalName)
                .authorities(this.authorities)
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        final AuthorizationConsent that = (AuthorizationConsent) o;
        return Objects.equals(authorizationConsentCode, that.authorizationConsentCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorizationConsentCode);
    }
}