package com.iokays.flowable.core.adapter.web.model;

import java.util.Date;
import java.util.Map;

/**
 * 流程任务模型 - 不可变数据载体类
 * @param id 任务ID
 * @param name 任务名称
 * @param description 任务描述
 * @param priority 优先级
 * @param owner 所有者
 * @param assignee 办理人
 * @param processInstanceId 流程实例ID
 * @param executionId 执行ID
 * @param processDefinitionId 流程定义ID
 * @param taskDefinitionKey 任务定义Key
 * @param createTime 创建时间
 * @param claimTime 签收时间
 * @param dueDate 到期时间
 * @param category 分类
 * @param tenantId 租户ID
 * @param formKey 表单Key
 * @param taskLocalVariables 任务局部变量
 * @param processVariables 流程变量
 */
public record ProcessTaskModel(
        String id,
        String name,
        String description,
        int priority,
        String owner,
        String assignee,
        String processInstanceId,
        String executionId,
        String processDefinitionId,
        String taskDefinitionKey,
        Date createTime,
        Date claimTime,
        Date dueDate,
        String category,
        String tenantId,
        String formKey,
        Map<String, Object> taskLocalVariables,
        Map<String, Object> processVariables
) {
    // 便捷方法：判断任务是否已分配
    public boolean isAssigned() {
        return assignee != null && !assignee.isEmpty();
    }

    // 便捷方法：判断任务是否已过期
    public boolean isOverdue() {
        return dueDate != null && dueDate.before(new Date());
    }

}
