package com.iokays.dispatch.core.adapter.job.webclient;

import lombok.Builder;

import java.util.Map;
import java.util.Objects;

@Builder
public record WebClientData(
    String url,
    String method,
    Map<String, String> headers,
    byte[] body,
    Map<String, Object> params
){}
