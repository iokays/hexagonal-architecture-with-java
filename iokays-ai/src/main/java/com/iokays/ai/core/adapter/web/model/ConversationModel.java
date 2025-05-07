package com.iokays.ai.core.adapter.web.model;

import lombok.Builder;

@Builder
public record ConversationModel(
        String conversationId,
        String message
) {
}