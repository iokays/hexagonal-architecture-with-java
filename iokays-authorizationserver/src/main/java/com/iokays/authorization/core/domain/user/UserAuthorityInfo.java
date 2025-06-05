package com.iokays.authorization.core.domain.user;

import com.iokays.authorization.core.domain.group.GroupId;
import lombok.Builder;

import java.util.List;

@Builder
public record UserAuthorityInfo(
        Username username,
        Password password,
        Boolean enabled,
        List<GroupId> groupIds,
        List<String> authorities
) {
}
