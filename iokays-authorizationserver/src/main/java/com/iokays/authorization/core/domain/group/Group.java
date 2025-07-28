package com.iokays.authorization.core.domain.group;

import com.google.common.collect.Lists;
import com.iokays.common.domain.jpa.AbstractJpaAggregateRoot;
import jakarta.persistence.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.Validate;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "t_groups")
public class Group extends AbstractJpaAggregateRoot<Group> {

    @Embedded
    @AttributeOverride(name = "code", column = @Column(name = "group_code", length = 40, unique = true, nullable = false))
    private GroupCode groupCode;

    private String groupName;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<GroupAuthority> authorities;

    protected Group() {
        super();
        this.authorities = Lists.newArrayList();
    }

    public Group(String groupName) {
        this();
        this.groupCode = GroupCode.makeGroupCode();
        this.groupName = Validate.notBlank(groupName, "groupName must not be blank");
    }

    public GroupCode groupCode() {
        return this.groupCode;
    }

    public GroupAuthInfo authInfo() {
        return GroupAuthInfo.builder()
                .groupId(this.groupCode)
                .groupName(this.groupName)
                .authorities(CollectionUtils.emptyIfNull(this.authorities).stream()
                        .map(GroupAuthority::authority).toList())
                .createdDate(this.getCreatedDate())
                .lastModifiedDate(this.getLastModifiedDate())
                .build();
    }

    public GroupAuthorityInfo authorityInfo() {
        return GroupAuthorityInfo.builder()
                .groupId(this.groupCode)
                .groupName(this.groupName)
                .authorities(CollectionUtils.emptyIfNull(this.authorities).stream().map(GroupAuthority::authority).toList())
                .createdDate(this.getCreatedDate())
                .lastModifiedDate(this.getLastModifiedDate())
                .build();
    }

    public void editAuthorities(final List<String> authorities) {
        Validate.notEmpty(authorities, "authorities must not be empty");

        if (null == this.authorities) {
            this.authorities = Lists.newArrayList();
        }

        this.authorities.clear();

        authorities
                .stream()
                .distinct()
                .map(v -> new GroupAuthority(this, v))
                .forEach(this.authorities::add);

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
        return Objects.equals(groupCode, group.groupCode);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(groupCode);
    }

}
