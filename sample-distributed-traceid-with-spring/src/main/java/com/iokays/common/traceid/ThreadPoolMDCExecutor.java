package com.iokays.common.traceid;

import org.apache.commons.collections4.MapUtils;
import org.slf4j.MDC;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * 在日志配置文件中<pattern>节点中以%X{trace-id}取出，比如：<pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level [uniqid-%X{trace-id}] %logger{50}-%line - %m%n</pattern>
 */
public class ThreadPoolMDCExecutor extends ThreadPoolTaskExecutor {

    private static <T> Callable<T> wrap(final Callable<T> callable, final Map<String, String> context) {
        return () -> {
            if (MapUtils.isEmpty(context)) {
                MDC.clear();
            } else {
                MDC.setContextMap(context);
            }
            try {
                return callable.call();
            } finally {
                // 清除子线程的，避免内存溢出，就和ThreadLocal.remove()一个原因
                MDC.clear();
            }
        };
    }

    private static Runnable wrap(final Runnable runnable, final Map<String, String> context) {
        return () -> {
            if (MapUtils.isEmpty(context)) {
                MDC.clear();
            } else {
                MDC.setContextMap(context);
            }
            try {
                runnable.run();
            } finally {
                MDC.clear();
            }
        };
    }

    @Override
    public void execute(Runnable task) {
        super.execute(wrap(task, MDC.getCopyOfContextMap()));
    }

    @Override
    public Future<?> submit(Runnable task) {
        return super.submit(wrap(task, MDC.getCopyOfContextMap()));
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        return super.submit(wrap(task, MDC.getCopyOfContextMap()));
    }
}
