package com.iokays.authorization.core.domain.groupmember;

import com.iokays.authorization.core.domain.group.GroupCode;
import com.iokays.authorization.core.domain.user.Username;
import com.iokays.common.domain.jpa.AbstractCode;
import jakarta.persistence.Embeddable;

@Embeddable
public class GroupMemberCode extends AbstractCode {

    protected GroupMemberCode() {
        super();
    }

    protected GroupMemberCode(GroupCode groupId, Username username) {
        super((groupId.code() + "#" + username.code()));
    }

}
