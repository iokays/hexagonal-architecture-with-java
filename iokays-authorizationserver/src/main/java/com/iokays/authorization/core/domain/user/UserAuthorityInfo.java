package com.iokays.authorization.core.domain.user;

import java.util.List;

public record UserAuthorityInfo(
        Username username,
        Password password,
        Boolean enabled,
        List<String> authorities
) {
}
