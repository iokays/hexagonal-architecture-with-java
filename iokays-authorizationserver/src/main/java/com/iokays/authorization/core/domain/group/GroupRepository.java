package com.iokays.authorization.core.domain.group;

import com.iokays.authorization.core.domain.user.Username;
import com.iokays.common.core.infrastructure.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GroupRepository extends Repository {

    Group findByGroupId(GroupId groupId);

    Group save(Group group);

    Group findByGroupName(String groupName);

    List<Group> findByMembersUsername(Username username);

    Page<Group> findAll(Pageable pageable);
}
