package com.iokays.authorization.core.domain.group;

import com.iokays.common.core.infrastructure.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GroupRepository extends Repository {

    Group findByGroupId(GroupId groupId);

    Group save(Group group);

    Group findByGroupName(String groupName);

    Page<Group> findAll(Pageable pageable);

    List<Group> findByGroupIdIn(List<GroupId> groupIds);
}
