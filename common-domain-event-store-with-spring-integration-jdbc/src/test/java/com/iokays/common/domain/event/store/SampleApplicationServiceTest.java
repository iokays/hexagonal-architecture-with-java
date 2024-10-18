package com.iokays.common.domain.event.store;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@Rollback(value = false)
@SpringBootTest
class SampleApplicationServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(SampleApplicationServiceTest.class);

    @Resource
    private SampleApplicationService service;

    @Test
    void testRun() {
        service.run();
    }

}