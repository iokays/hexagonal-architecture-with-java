package com.iokays.dispatch.core.adapter.job.rest;

import lombok.Builder;

import java.io.Serializable;
import java.util.Map;

@Builder
public record RestClientData(
        String url,
        String method,
        Map<String, String> headers,
        byte[] body,
        Map<String, ? extends Serializable> params
) implements Serializable {
}
