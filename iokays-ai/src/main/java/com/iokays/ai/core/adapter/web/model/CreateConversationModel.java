package com.iokays.ai.core.adapter.web.model;

import lombok.Builder;

@Builder
public record CreateConversationModel(
        String act,
        String prompt
) {
}