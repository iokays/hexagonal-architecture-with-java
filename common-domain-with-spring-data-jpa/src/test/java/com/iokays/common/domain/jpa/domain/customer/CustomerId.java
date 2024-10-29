package com.iokays.common.domain.jpa.domain.customer;

import com.iokays.common.domain.jpa.AbstractId;
import jakarta.persistence.Embeddable;
import org.apache.commons.lang3.Validate;

import java.util.UUID;

@Embeddable
public class CustomerId extends AbstractId {

    private static final String UUID_REGEX = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";

    private CustomerId() {
        super();
    }

    public CustomerId(String id) {
        super(id);
    }

    public static CustomerId makeCustomerId() {
        return new CustomerId(UUID.randomUUID().toString());
    }

    /**
     * 验证是否是UUID字符串
     *
     * @param anId
     */
    @Override
    protected void validateId(String anId) {
        Validate.notNull(anId);
        Validate.isTrue(anId.matches(UUID_REGEX));
    }
}
