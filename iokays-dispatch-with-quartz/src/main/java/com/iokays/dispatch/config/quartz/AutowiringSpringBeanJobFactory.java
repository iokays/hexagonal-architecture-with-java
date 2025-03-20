package com.iokays.dispatch.config.quartz;

import lombok.AllArgsConstructor;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AutowiringSpringBeanJobFactory extends SpringBeanJobFactory {

    private final ApplicationContext applicationContext;

    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        // 通过 Spring 容器创建 Job 实例
        Object job = super.createJobInstance(bundle);
        applicationContext.getAutowireCapableBeanFactory().autowireBean(job);
        return job;
    }

}
