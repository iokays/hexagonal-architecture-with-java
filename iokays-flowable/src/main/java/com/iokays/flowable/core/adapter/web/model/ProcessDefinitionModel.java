package com.iokays.flowable.core.adapter.web.model;

public record ProcessDefinitionModel(
        String id,
        String category,
        String name,
        String key,
        String description,
        int version,
        String resourceName,
        String deploymentId,
        String diagramResourceName,
        String tenantId,
        String derivedFrom,
        String derivedFromRoot,
        int derivedVersion,
        String engineVersion
) {
    // record会自动生成以下方法：
    // 1. 构造函数
    // 2. 所有属性的getter方法（形式为id(), category()等，而不是getId()）
    // 3. equals(), hashCode(), toString()
}
