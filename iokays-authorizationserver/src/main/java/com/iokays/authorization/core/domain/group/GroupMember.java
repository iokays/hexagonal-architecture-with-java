package com.iokays.authorization.core.domain.group;

import com.iokays.authorization.core.domain.user.Username;
import com.iokays.common.domain.jpa.IdentifiedValueObject;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "t_group_members")
public class GroupMember extends IdentifiedValueObject<GroupMember> {

    @ManyToOne
    @JoinColumn(name = "groupId", referencedColumnName = "group_id")
    private Group group;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "username", length = 40, nullable = false))
    private Username username;

    protected GroupMember() {
        super();
    }

    public GroupMember(Group group, Username username) {
        this.group = Objects.requireNonNull(group);
        this.username = Objects.requireNonNull(username);
    }

    protected void unLink() {
        this.group = null;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        final GroupMember that = (GroupMember) o;
        return Objects.equals(group, that.group) && Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(group, username);
    }
}
