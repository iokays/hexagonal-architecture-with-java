package com.iokays.dispatch.config.quartz;

import org.springframework.boot.autoconfigure.quartz.SchedulerFactoryBeanCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    @Bean
    public SchedulerFactoryBeanCustomizer schedulerFactory(final AutowiringSpringBeanJobFactory jobFactory) {
        return schedulerFactoryBean -> schedulerFactoryBean.setJobFactory(jobFactory);
    }
}