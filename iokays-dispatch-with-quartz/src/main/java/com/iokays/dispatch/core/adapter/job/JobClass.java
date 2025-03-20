package com.iokays.dispatch.core.adapter.job;

import lombok.Getter;
import org.quartz.Job;

@Getter
public enum JobClass {

    SAMPLE(SampleJob.class);

    private Class<? extends Job> clazz;

    JobClass(Class<? extends Job> clazz) {
        this.clazz = clazz;
    }

}
