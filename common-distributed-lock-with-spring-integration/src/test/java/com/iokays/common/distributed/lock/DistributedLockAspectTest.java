package com.iokays.common.distributed.lock;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;


@SpringBootTest
class DistributedLockAspectTest {

    @Resource
    private SampleService service;

    @Test
    void test() {
        Stream.generate(() -> "key_1").limit(10).parallel().forEach(service::run);
    }

}