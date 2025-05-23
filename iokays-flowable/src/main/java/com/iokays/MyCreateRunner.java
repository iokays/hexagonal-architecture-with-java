package com.iokays;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Order
@Component
@AllArgsConstructor
public class MyCreateRunner implements CommandLineRunner {

    private final RuntimeService runtimeService;
    private final RepositoryService repositoryService;

    @Override
    public void run(String... args) {
        // 通过流程定义Key启动（使用最新版本）
        ProcessInstance processInstance = runtimeService.createProcessInstanceBuilder()
                .processDefinitionKey("oneTaskProcess")     // 流程定义Key
                .businessKey("order-123")                   // 业务Key（可选）
                .name("订单审批流程实例")   // 实例名称（可选）
                .start();
    }
}
