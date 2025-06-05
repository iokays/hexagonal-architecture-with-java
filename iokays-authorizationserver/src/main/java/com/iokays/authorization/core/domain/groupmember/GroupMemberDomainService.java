package com.iokays.authorization.core.domain.groupmember;

import com.iokays.authorization.core.domain.group.GroupId;
import com.iokays.authorization.core.domain.user.Username;
import com.iokays.common.core.service.DomainService;

import java.util.List;
import java.util.Map;

public interface GroupMemberDomainService extends DomainService {

    Map<GroupId, List<String>> getGroupAuthorities(Username username);

    void create(GroupId groupId, Username... username);

}
