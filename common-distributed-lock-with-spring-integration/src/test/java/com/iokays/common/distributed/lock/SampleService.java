package com.iokays.common.distributed.lock;

import com.iokays.common.core.lock.DistributedLock;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

import static io.vavr.API.println;

@Service
public class SampleService {

    private final AtomicInteger counter = new AtomicInteger(0);

    @DistributedLock(value = "sampleLock", key = "#key", time = 1)
    public void run(final String key) {
        println(STR."counter: \{counter.getAndIncrement()}");
    }

}
