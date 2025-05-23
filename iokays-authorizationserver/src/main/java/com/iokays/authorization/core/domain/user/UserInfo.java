package com.iokays.authorization.core.domain.user;

public record UserInfo(Username username, Password password, Boolean enabled) {
}
