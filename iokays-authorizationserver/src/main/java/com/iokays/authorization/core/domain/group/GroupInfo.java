package com.iokays.authorization.core.domain.group;

import lombok.Builder;

import java.util.List;

@Builder
public record GroupInfo(
        GroupId groupId,
        String groupName,
        List<String> authorities
) {
}
