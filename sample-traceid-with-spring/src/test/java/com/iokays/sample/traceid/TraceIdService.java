package com.iokays.sample.traceid;

import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

@Service
public class TraceIdService {

    private static final Logger log = LoggerFactory.getLogger(TraceIdServiceTest.class);

    @Resource
    private TaskExecutor taskExecutor;

    void run() {
        log.info("main.info");
        taskExecutor.execute(() -> log.info("taskExecutor.execute.info"));
    }


}
