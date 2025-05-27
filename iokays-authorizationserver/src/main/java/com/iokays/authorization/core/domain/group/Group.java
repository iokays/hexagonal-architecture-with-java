package com.iokays.authorization.core.domain.group;

import com.google.common.collect.Lists;
import com.iokays.authorization.core.domain.user.Username;
import com.iokays.common.domain.jpa.AbstractAggregateRoot;
import jakarta.persistence.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "t_groups")
public class Group extends AbstractAggregateRoot<Group> {

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "group_id", length = 40, unique = true, nullable = false))
    private GroupId groupId;

    private String groupName;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<GroupAuthority> authorities;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<GroupMember> members;

    protected Group() {
        super();
        this.authorities = Lists.newArrayList();
        this.members = Lists.newArrayList();
    }

    public Group(String groupName, List<String> authorities) {
        this();
        this.groupId = GroupId.makeGroupId();
        this.groupName = Validate.notBlank(groupName, "groupName must not be blank");
        this.addAuthorities(CollectionUtils.emptyIfNull(authorities).toArray(new String[]{}));
    }

    public GroupId groupId() {
        return this.groupId;
    }

    public GroupAuthInfo authInfo() {
        return GroupAuthInfo.builder()
                .groupId(this.groupId)
                .groupName(this.groupName)
                .authorities(CollectionUtils.emptyIfNull(this.authorities).stream()
                        .map(GroupAuthority::authority).toList())
                .createdDate(this.getCreatedDate())
                .lastModifiedDate(this.getLastModifiedDate())
                .build();
    }

    public GroupAuthorityInfo authorityInfo() {
        return GroupAuthorityInfo.builder()
                .groupId(this.groupId)
                .groupName(this.groupName)
                .authorities(CollectionUtils.emptyIfNull(this.authorities).stream().map(GroupAuthority::authority).toList())
                .createdDate(this.getCreatedDate())
                .lastModifiedDate(this.getLastModifiedDate())
                .build();
    }

    public void addMember(final Username... username) {
        if (null == this.members) {
            this.members = Lists.newArrayList();
        }

        final List<GroupMember> list = Arrays.stream(username)
                .map(v -> new GroupMember(this, v))
                .filter(v -> !this.members.contains(v)).toList();

        if (CollectionUtils.isNotEmpty(list)) {
            this.members.addAll(list);
        }
    }

    public void addAuthorities(final String... authorities) {
        if (null == this.authorities) {
            this.authorities = Lists.newArrayList();
        }
        final List<GroupAuthority> list = Arrays.stream(authorities)
                .filter(StringUtils::isNotBlank)
                .map(v -> new GroupAuthority(this, v))
                .filter(v -> !this.authorities.contains(v)).toList();

        if (CollectionUtils.isNotEmpty(list)) {
            this.authorities.addAll(list);
        }

    }

    public List<String> authorities() {
        return CollectionUtils.emptyIfNull(this.authorities)
                .stream()
                .map(GroupAuthority::authority).toList();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        final Group group = (Group) o;
        return Objects.equals(groupId, group.groupId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(groupId);
    }

}
