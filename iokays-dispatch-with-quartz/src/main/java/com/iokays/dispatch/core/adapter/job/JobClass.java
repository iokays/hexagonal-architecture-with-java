package com.iokays.dispatch.core.adapter.job;

import lombok.Getter;
import org.quartz.Job;

import java.util.Arrays;

@Getter
public enum JobClass {

    SAMPLE(SampleJob.class);

    private final Class<? extends Job> clazz;

    JobClass(Class<? extends Job> clazz) {
        this.clazz = clazz;
    }

    public static JobClass of(String className) {
        return Arrays.stream(JobClass.values()).filter(jobClass -> jobClass.getClazz().getName().equals(className))
                .findFirst().orElse(null);
    }

}
