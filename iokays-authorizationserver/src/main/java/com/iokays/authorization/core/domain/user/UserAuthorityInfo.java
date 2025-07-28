package com.iokays.authorization.core.domain.user;

import com.iokays.authorization.core.domain.group.GroupCode;
import lombok.Builder;

import java.util.List;

@Builder
public record UserAuthorityInfo(
        Username username,
        Password password,
        Boolean enabled,
        List<GroupCode> groupIds,
        List<String> authorities
) {
}
