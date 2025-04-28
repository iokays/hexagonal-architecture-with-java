package com.iokays.ai.core.adapter.web.model;

import javax.validation.constraints.NotNull;

public record ConversionModel(
        @NotNull String conversationId,
        @NotNull String message
) { }
