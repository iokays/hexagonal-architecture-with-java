package com.iokays.sample.traceid;

import com.iokays.common.traceid.ThreadPoolMDCExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池配置。把ThreadPoolTaskExecutor换成ThreadPoolMdcExecutor
 **/
@Configuration
public class ThreadPoolConfig {

    /**
     * 业务用到的线程池
     *
     * @return
     */
    @Bean(name = "threadPoolTaskExecutor")
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        final var executor = new ThreadPoolMDCExecutor();
        executor.setCorePoolSize(10); // 核心线程数
        executor.setMaxPoolSize(20); // 最大线程数
        executor.setQueueCapacity(500); // 队列大小
        executor.setKeepAliveSeconds(60); // 线程空闲时的存活时间
        executor.setThreadNamePrefix("MyThreadPoolTaskExecutor-"); // 线程名称前缀
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy()); // 拒绝策略
        executor.initialize(); // 初始化
        return executor;
    }
}
