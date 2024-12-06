package com.iokays.core.domain.authorization;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class AuthorizationGrantScopes extends AuthorizationGrantMetadata {

    private String scopes;

    @Override
    public boolean sameValueAs(AuthorizationGrantMetadata other) {
        return super.sameValueAs(other) && Objects.equals(scopes, ((AuthorizationGrantScopes) other).scopes);
    }

}
