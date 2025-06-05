package com.iokays.authorization.core.domain.groupmember;

import com.iokays.authorization.core.domain.group.GroupId;
import com.iokays.authorization.core.domain.user.Username;
import com.iokays.common.domain.jpa.IdentifiedEntity;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "t_group_members")
public class GroupMember extends IdentifiedEntity<GroupMember> {

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "group_member_id", length = 80, unique = true, nullable = false))
    private GroupMemberId groupMemberId;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "group_id", length = 40, nullable = false))
    private GroupId groupId;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "username", length = 40, nullable = false))
    private Username username;

    protected GroupMember() {
        super();
    }

    public GroupMember(GroupId groupId, Username username) {
        this();
        this.groupMemberId = new GroupMemberId(groupId, username);
        this.username = username;
        this.groupId = groupId;
    }

    public GroupId groupId() {
        return this.groupId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        final GroupMember that = (GroupMember) o;
        return Objects.equals(groupMemberId, that.groupMemberId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupMemberId);
    }
}
