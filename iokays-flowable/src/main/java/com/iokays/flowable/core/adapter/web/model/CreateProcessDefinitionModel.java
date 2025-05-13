package com.iokays.flowable.core.adapter.web.model;

public record CreateProcessDefinitionModel(
        String resourceName,
        byte[] bytes
) {
}
