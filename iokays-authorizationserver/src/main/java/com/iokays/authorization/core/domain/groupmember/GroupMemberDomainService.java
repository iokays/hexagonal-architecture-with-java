package com.iokays.authorization.core.domain.groupmember;

import com.iokays.authorization.core.domain.group.GroupId;
import com.iokays.authorization.core.domain.user.Username;
import com.iokays.common.core.service.DomainService;

import java.util.List;

public interface GroupMemberDomainService extends DomainService {

    List<String> getGroupAuthorities(Username username);

    void create(GroupId groupId, Username... username);

}
