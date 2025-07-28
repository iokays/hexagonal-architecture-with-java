package com.iokays.authorization.core.domain.groupmember;

import com.iokays.authorization.core.domain.group.GroupCode;
import com.iokays.authorization.core.domain.user.Username;
import com.iokays.common.domain.jpa.IdentifiedEntity;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "t_group_members")
public class GroupMember extends IdentifiedEntity<GroupMember> {

    @Embedded
    @AttributeOverride(name = "code", column = @Column(name = "group_member_code", length = 80, unique = true, nullable = false))
    private GroupMemberCode groupMemberCode;

    @Embedded
    @AttributeOverride(name = "code", column = @Column(name = "group_code", length = 40, nullable = false))
    private GroupCode groupCode;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "username", length = 40, nullable = false))
    private Username username;

    protected GroupMember() {
        super();
    }

    public GroupMember(GroupCode groupCode, Username username) {
        this();
        this.groupMemberCode = new GroupMemberCode(groupCode, username);
        this.username = username;
        this.groupCode = groupCode;
    }

    public GroupCode groupCode() {
        return this.groupCode;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        final GroupMember that = (GroupMember) o;
        return Objects.equals(groupMemberCode, that.groupMemberCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupMemberCode);
    }
}
