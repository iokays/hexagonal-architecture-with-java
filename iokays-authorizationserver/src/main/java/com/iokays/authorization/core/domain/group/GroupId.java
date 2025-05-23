package com.iokays.authorization.core.domain.group;

import com.iokays.common.domain.jpa.AbstractId;
import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public class GroupId extends AbstractId {

    protected GroupId() {
        super();
    }

    protected GroupId(String id) {
        super(id);
    }

    public static GroupId makeGroupId() {
        return new GroupId(UUID.randomUUID().toString());
    }

    public static GroupId of(final String groupId) {
        return new GroupId(groupId);
    }

}
