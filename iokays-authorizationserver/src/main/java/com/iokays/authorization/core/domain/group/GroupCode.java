package com.iokays.authorization.core.domain.group;

import com.iokays.common.domain.jpa.AbstractCode;
import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public class GroupCode extends AbstractCode {

    protected GroupCode() {
        super();
    }

    protected GroupCode(String id) {
        super(id);
    }

    public static GroupCode makeGroupCode() {
        return new GroupCode(UUID.randomUUID().toString());
    }

    public static GroupCode of(final String groupId) {
        return new GroupCode(groupId);
    }

}
