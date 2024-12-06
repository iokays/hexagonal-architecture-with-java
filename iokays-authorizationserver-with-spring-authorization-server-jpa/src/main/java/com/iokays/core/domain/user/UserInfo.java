package com.iokays.core.domain.user;

public record UserInfo(UserId userId, String username, Password password, Boolean enabled) {
}
