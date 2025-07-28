package com.iokays.authorization.core.domain.groupmember;

import com.iokays.authorization.core.domain.group.GroupCode;
import com.iokays.authorization.core.domain.user.Username;
import com.iokays.common.core.service.DomainService;

import java.util.List;
import java.util.Map;

public interface GroupMemberDomainService extends DomainService {

    Map<GroupCode, List<String>> getGroupAuthorities(Username username);

    void create(Username username, List<GroupCode> groupIds);

    void delete(Username username);

}
