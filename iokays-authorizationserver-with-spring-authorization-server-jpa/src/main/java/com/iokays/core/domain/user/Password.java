package com.iokays.core.domain.user;

import com.iokays.common.core.domain.ValueObject;
import jakarta.persistence.Embeddable;
import org.apache.commons.lang3.Validate;

@Embeddable
public class Password implements ValueObject<Password> {

    private String value;

    protected Password() {
        super();
    }

    public Password(String rawPassword) {
        this();
        Validate.notEmpty(rawPassword, "密码不能为空");
        this.value = DomainRegistry.passwordService().encode(rawPassword);
    }

    @Override
    public boolean sameValueAs(Password other) {
        return this.value.equals(other.value);
    }

    public String getValue() {
        return value;
    }
}
