package com.iokays.authorization.core.domain.group;

import com.iokays.authorization.core.domain.user.Username;
import com.iokays.common.core.infrastructure.Repository;

import java.util.List;

public interface GroupRepository extends Repository {

    Group findByGroupId(GroupId groupId);

    Group save(Group group);

    Group findByGroupName(String groupName);

    List<Group> findByMembersUsername(Username username);

}
