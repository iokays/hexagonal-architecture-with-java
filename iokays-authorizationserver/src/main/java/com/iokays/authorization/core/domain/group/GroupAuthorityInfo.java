package com.iokays.authorization.core.domain.group;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record GroupAuthorityInfo(
        GroupId groupId,
        String groupName,
        List<String> authorities,
        LocalDateTime createdDate,
        LocalDateTime lastModifiedDate
) {
}
