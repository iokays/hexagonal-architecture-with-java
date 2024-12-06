package com.iokays.core.domain.user;

import com.iokays.common.core.domain.ValueObject;
import jakarta.persistence.Embeddable;

@Embeddable
public record NickName(String nickName) implements ValueObject<NickName> {

    @Override
    public boolean sameValueAs(NickName other) {
        return this.nickName.equals(other.nickName);
    }
}
