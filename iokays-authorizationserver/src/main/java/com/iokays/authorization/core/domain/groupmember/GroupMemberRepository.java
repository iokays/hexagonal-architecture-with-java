package com.iokays.authorization.core.domain.groupmember;

import com.iokays.authorization.core.domain.group.GroupCode;
import com.iokays.authorization.core.domain.user.Username;
import com.iokays.common.core.infrastructure.Repository;

import java.util.List;

public interface GroupMemberRepository extends Repository {

    GroupMember save(GroupMember groupMember);

    void delete(GroupMember groupMember);

    void deleteByUsername(Username username);

    List<GroupMember> findByGroupId(GroupCode groupId);

    List<GroupMember> findByUsername(Username username);

    GroupMember findByGroupIdAndUsername(GroupCode groupId, Username username);


}
