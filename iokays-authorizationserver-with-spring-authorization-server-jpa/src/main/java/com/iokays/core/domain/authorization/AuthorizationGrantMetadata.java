package com.iokays.core.domain.authorization;

import com.iokays.common.core.domain.ValueObject;
import jakarta.persistence.Embeddable;

import java.time.Instant;
import java.util.Objects;

@Embeddable
public abstract class AuthorizationGrantMetadata implements ValueObject<AuthorizationGrantMetadata> {

    private String value;
    private Instant issuedAt;
    private Instant expiresAt;
    private String metadata;

    @Override
    public boolean sameValueAs(AuthorizationGrantMetadata other) {
        return Objects.equals(value, other.value)
                && Objects.equals(issuedAt, other.issuedAt)
                && Objects.equals(expiresAt, other.expiresAt)
                && Objects.equals(metadata, other.metadata)
                ;
    }

}
