package com.iokays.authorization.core.domain.user;

import com.iokays.authorization.core.domain.DomainRegistry;
import com.iokays.authorization.core.domain.group.GroupId;
import com.iokays.authorization.core.domain.user.event.UserDeleted;
import com.iokays.authorization.core.domain.user.event.UserGroupUpdated;
import com.iokays.authorization.core.domain.user.event.UserRegistered;
import com.iokays.common.core.event.EventId;
import com.iokays.common.domain.jpa.AbstractId;
import com.iokays.common.domain.jpa.AbstractJpaAggregateRoot;
import jakarta.persistence.*;
import org.apache.commons.collections4.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "t_users")
public class User extends AbstractJpaAggregateRoot<User> {

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "username", length = 40, unique = true, nullable = false))
    private Username username;

    @Embedded
    @Column(length = 255, nullable = false)
    @AttributeOverride(name = "value", column = @Column(name = "password"))
    private Password password;

    @Column(nullable = false)
    private Boolean enabled;

    protected User() {
        super();
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
        final var groupAuthorities = DomainRegistry.groupMemberDomainService().getGroupAuthorities(this.username);
        return UserAuthorityInfo.builder()
                .username(this.username)
                .password(this.password)
                .enabled(this.enabled)
                .groupIds(groupAuthorities.keySet().stream().distinct().toList())
                .authorities(groupAuthorities.values().stream().flatMap(List::stream).distinct().toList())
                .build();
    }

    public Boolean enabled() {
        return enabled;
    }

    public Password password() {
        return password;
    }

    public void group(final List<GroupId> groupIds) {
        DomainRegistry.groupMemberDomainService().delete(this.username);
        DomainRegistry.groupMemberDomainService().create(this.username, groupIds);

        //添加权限变更事件
        this.andEvent(new UserGroupUpdated(
                EventId.generate(),
                this.username.id(),
                CollectionUtils.emptyIfNull(groupIds).stream().map(AbstractId::id).toList(),
                LocalDateTime.now())
        );
    }

    @PostRemove
    private void afterDelete() {
        this.andEvent(new UserDeleted(EventId.generate(), this.username.id(), LocalDateTime.now()));
    }

}
