package com.iokays.common.distributed.lock;

import com.iokays.common.core.lock.DistributedLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
public class SampleService {

    private final AtomicInteger counter = new AtomicInteger(0);

    @DistributedLock(value = "sampleLock", key = "#key", time = 1)
    public void run(final String key) {
        log.info("run: {}, counter: {}", key, counter.getAndIncrement());
    }

}
