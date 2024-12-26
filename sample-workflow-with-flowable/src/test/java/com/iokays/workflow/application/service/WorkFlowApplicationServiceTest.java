package com.iokays.workflow.application.service;


import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class WorkFlowApplicationServiceTest {

    @Resource
    WorkFlowApplicationService workFlowApplicationService;


    @Test
    void testInfo() {
        workFlowApplicationService.info();
        Assertions.assertTrue(true);
    }

}