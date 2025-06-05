package com.iokays.authorization.core.domain.groupmember;

import com.iokays.authorization.core.domain.group.GroupId;
import com.iokays.authorization.core.domain.user.Username;
import com.iokays.common.domain.jpa.AbstractId;
import jakarta.persistence.Embeddable;

@Embeddable
public class GroupMemberId extends AbstractId {

    protected GroupMemberId() {
        super();
    }

    protected GroupMemberId(GroupId groupId, Username username) {
        super((groupId.id() + "#" + username.id()));
    }

}
