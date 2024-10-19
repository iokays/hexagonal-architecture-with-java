package com.iokays.common.distributed.lock;


import jakarta.annotation.Resource;
import org.apache.commons.lang3.Validate;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.integration.support.locks.LockRegistry;

import java.util.concurrent.TimeUnit;

@SpringBootTest
public class LockRegistryTest {

    @Resource
    private LockRegistry lockRegistry;

    /**
     * 简单锁测试
     */
    @Test
    public void testLock() {
        final var orderLock = lockRegistry.obtain("ORDER_NO_00001");
        orderLock.lock();
        System.out.println("执行操作");
        orderLock.unlock();
    }

    /**
     * 带有时间的加锁测试
     *
     * @throws InterruptedException
     */
    @Test
    public void testTryLock() throws InterruptedException {
        final var orderLock = lockRegistry.obtain("ORDER_NO_00002");

        // 1秒内尝试获取锁，失败返回false
        final var mark = orderLock.tryLock(1, TimeUnit.SECONDS);
        Validate.isTrue(mark, "获取锁失败");

        System.out.println("执行操作");
        orderLock.unlock();

    }
}
