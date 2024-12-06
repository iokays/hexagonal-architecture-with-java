package com.iokays.core.domain.authorization;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class AuthorizationGrantClaims extends AuthorizationGrantMetadata {

    private String claims;

    @Override
    public boolean sameValueAs(AuthorizationGrantMetadata other) {
        return super.sameValueAs(other) && Objects.equals(claims, ((AuthorizationGrantClaims) other).claims);
    }
}
