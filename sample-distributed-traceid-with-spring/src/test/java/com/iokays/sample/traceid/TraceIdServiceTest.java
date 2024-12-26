package com.iokays.sample.traceid;

import com.iokays.common.traceid.JunitMDCSupport;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TraceIdServiceTest implements JunitMDCSupport {

    private static final Logger log = LoggerFactory.getLogger(TraceIdServiceTest.class);

    @Resource
    private TraceIdService traceIdService;

    @Test
    void testRun() {
        traceIdService.run();
    }

    @Test
    void testRun2() {
        traceIdService.run();
    }


}
