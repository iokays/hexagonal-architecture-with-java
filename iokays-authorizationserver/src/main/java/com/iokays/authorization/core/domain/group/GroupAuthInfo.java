package com.iokays.authorization.core.domain.group;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record GroupAuthInfo(
        GroupId groupId,
        String groupName,
        List<String> authorities,
        LocalDateTime createdDate,
        LocalDateTime lastModifiedDate
) {
}
