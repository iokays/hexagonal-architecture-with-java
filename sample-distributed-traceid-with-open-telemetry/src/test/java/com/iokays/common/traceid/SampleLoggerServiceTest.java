package com.iokays.common.traceid;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SampleLoggerServiceTest {


    @Resource
    private SampleLoggerService service;

    @Test
    void sampleTest() {
        service.show();
    }

}
