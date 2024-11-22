package com.iokays.common.traceid;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static io.vavr.API.println;


@Aspect
@Component
public class DriverAdapterWithMDCAspect {

    private static final String TRACE_ID = TRACE.ID;

    /**
     * Spring 前置通知 实现traceId的设置
     */
    @Before("@within(com.iokays.common.core.adapter.DriverAdapter)")
    public void before(final JoinPoint joinPoint) {
        println("Before method execution: " + joinPoint.getSignature().getName());
        // 从 MDC 中获取 traceId
        final String traceId = MDC.get(TRACE_ID);
        if (StringUtils.isEmpty(traceId)) {
            // 设置 traceId 到 DriverAdapter
            MDC.put(TRACE_ID, UUID.randomUUID().toString());
        }
    }

}
