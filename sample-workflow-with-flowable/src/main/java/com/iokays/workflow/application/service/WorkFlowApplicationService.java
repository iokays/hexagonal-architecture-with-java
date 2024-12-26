package com.iokays.workflow.application.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.TaskService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class WorkFlowApplicationService {

    private final RepositoryService repositoryService;
    private final TaskService taskService;

    public void info() {
        log.info("Number of process definitions : {}", repositoryService.createProcessDefinitionQuery().count());
        log.info("Number of tasks :  : {}", taskService.createTaskQuery().count());
    }

}
