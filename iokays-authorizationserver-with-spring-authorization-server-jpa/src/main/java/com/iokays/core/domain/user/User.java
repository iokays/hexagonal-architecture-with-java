package com.iokays.core.domain.user;

import com.iokays.common.core.event.EventId;
import com.iokays.common.domain.jpa.AbstractAggregateRoot;
import com.iokays.core.domain.user.event.UserRegistered;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "t_users")
public class User extends AbstractAggregateRoot<User> {

    @AttributeOverride(name = "id", column = @Column(name = "user_id", length = 40, nullable = false))
    private UserId userId;

    @Column(length = 255, nullable = false, unique = true)
    private String username;

    @Column(length = 255, nullable = false)
    @AttributeOverride(name = "value", column = @Column(name = "password"))
    private Password password;

    @Column(nullable = false)
    private Boolean enabled;

    protected User() {
        super();
    }

    public User(String username, Password password) {
        this();
        this.userId = UserId.makeUserId();
        this.username(username);
        this.password(password);
        this.enabled(true);
        //添加事件
        this.andEvent(new UserRegistered(EventId.generate(), this.userId, this.username, this.enabled, LocalDateTime.now()));
    }

    private void username(final String username) {
        this.username = Objects.requireNonNull(username);
    }


    private void password(Password password) {
        this.password = Objects.requireNonNull(password);
    }

    private void enabled(Boolean enabled) {
        this.enabled = Objects.requireNonNull(enabled);
    }

    @Override
    public boolean sameIdentityAs(User other) {
        return this.userId.equals(other.userId);
    }

    public UserId getUserId() {
        return userId;
    }

    public UserInfo toUserInfo() {
        return new UserInfo(this.userId, this.username, this.password, this.enabled);
    }

    public String getUsername() {
        return username;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public Password getPassword() {
        return password;
    }
}
