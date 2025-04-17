package com.iokays.dispatch.core.adapter.job;

import com.iokays.dispatch.core.adapter.job.rest.RestClientJob;
import lombok.Getter;
import org.quartz.Job;

import java.util.Arrays;

@Getter
public enum JobClass {

    SAMPLE(SampleJob.class)
    ,REST_CLIENT(RestClientJob.class);

    private final Class<? extends Job> clazz;

    JobClass(Class<? extends Job> clazz) {
        this.clazz = clazz;
    }

    public static JobClass of(String className) {
        return Arrays.stream(JobClass.values()).filter(jobClass -> jobClass.getClazz().getName().equals(className))
                .findFirst().orElse(null);
    }

}
