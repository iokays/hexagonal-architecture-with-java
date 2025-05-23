package com.iokays.authorization.core.domain.group;

import com.iokays.common.domain.jpa.IdentifiedValueObject;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.apache.commons.lang3.Validate;

import java.util.Objects;

@Entity
@Table(name = "t_group_authorities")
public class GroupAuthority extends IdentifiedValueObject<GroupAuthority> {

    @ManyToOne
    @JoinColumn(name = "groupId", referencedColumnName = "group_id")
    private Group group;

    private String authority;

    protected GroupAuthority() {
        super();
    }

    public GroupAuthority(Group group, String authority) {
        this();
        this.group = Objects.requireNonNull(group);
        this.authority = Validate.notBlank(authority);
    }

    public String authority() {
        return this.authority;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        final GroupAuthority that = (GroupAuthority) o;
        return Objects.equals(group, that.group) && Objects.equals(authority, that.authority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(group, authority);
    }
}
