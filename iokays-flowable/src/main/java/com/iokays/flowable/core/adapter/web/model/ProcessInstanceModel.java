package com.iokays.flowable.core.adapter.web.model;

import java.util.Date;
import java.util.Map;

/**
 * 流程实例模型 - 不可变数据载体类
 * @param processDefinitionId 流程定义ID
 * @param processDefinitionName 流程定义名称
 * @param processDefinitionKey 流程定义Key
 * @param processDefinitionVersion 流程定义版本
 * @param processDefinitionCategory 流程定义分类
 * @param deploymentId 部署ID
 * @param businessKey 业务Key
 * @param businessStatus 业务状态
 * @param suspended 是否挂起
 * @param processVariables 流程变量
 * @param tenantId 租户ID
 * @param name 名称
 * @param description 描述
 * @param localizedName 本地化名称
 * @param localizedDescription 本地化描述
 * @param startTime 开始时间
 * @param startUserId 启动用户ID
 * @param callbackId 回调ID
 * @param callbackType 回调类型
 */
public record ProcessInstanceModel(
        String processDefinitionId,
        String processDefinitionName,
        String processDefinitionKey,
        Integer processDefinitionVersion,
        String processDefinitionCategory,
        String deploymentId,
        String businessKey,
        String businessStatus,
        boolean suspended,
        Map<String, Object> processVariables,
        String tenantId,
        String name,
        String description,
        String localizedName,
        String localizedDescription,
        Date startTime,
        String startUserId,
        String callbackId,
        String callbackType
) {
    // 可以添加便捷方法
    public boolean isActive() {
        return !suspended;
    }

}
