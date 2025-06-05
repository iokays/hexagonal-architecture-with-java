package com.iokays.authorization.core.domain.user;

import com.google.common.collect.Lists;
import com.iokays.authorization.core.domain.DomainRegistry;
import com.iokays.authorization.core.domain.group.GroupId;
import com.iokays.authorization.core.domain.user.event.UserRegistered;
import com.iokays.common.core.event.EventId;
import com.iokays.common.domain.jpa.AbstractAggregateRoot;
import jakarta.persistence.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "t_users")
public class User extends AbstractAggregateRoot<User> {

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "username", length = 40, unique = true, nullable = false))
    private Username username;

    @Embedded
    @Column(length = 255, nullable = false)
    @AttributeOverride(name = "value", column = @Column(name = "password"))
    private Password password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Authority> authorities;

    @Column(nullable = false)
    private Boolean enabled;

    protected User() {
        super();
        this.authorities = Lists.newArrayList();
    }

    public User(Username username, Password password) {
        this();
        this.username(username);
        this.password(password);
        this.enabled(true);
        //添加事件
        this.andEvent(new UserRegistered(EventId.generate(), this.username.id(), this.enabled, LocalDateTime.now()));
    }

    private void username(final Username username) {
        this.username = Objects.requireNonNull(username);
    }


    private void password(Password password) {
        this.password = Objects.requireNonNull(password);
    }

    private void enabled(Boolean enabled) {
        this.enabled = Objects.requireNonNull(enabled);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        final User user = (User) o;
        return Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(username);
    }

    public Username getUserName() {
        return username;
    }

    public UserInfo toUserInfo() {
        return new UserInfo(this.username, this.password, this.enabled);
    }

    public UserAuthorityInfo toUserAuthInfo() {
        return new UserAuthorityInfo(
                this.username,
                this.password,
                this.enabled,
                ListUtils.union(this.authorities(), this.groupAuthorities()).stream().distinct().toList());
    }

    public Boolean enabled() {
        return enabled;
    }

    public Password password() {
        return password;
    }

    public List<String> authorities() {
        return CollectionUtils.emptyIfNull(this.authorities)
                .stream()
                .map(Authority::authority).toList();
    }

    public List<String> groupAuthorities() {
        return DomainRegistry.groupMemberDomainService().getGroupAuthorities(this.username);
    }

    public void addGroup(final GroupId groupId) {
        DomainRegistry.groupMemberDomainService().create(groupId, this.username);
    }

}
