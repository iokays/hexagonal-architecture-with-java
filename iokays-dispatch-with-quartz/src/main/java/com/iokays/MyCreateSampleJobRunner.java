package com.iokays;

import com.iokays.dispatch.core.adapter.job.JobClass;
import com.iokays.dispatch.core.application.service.JobApplicationService;
import com.iokays.dispatch.core.application.service.command.CreateJob;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Order
@Component
@AllArgsConstructor
public class MyCreateSampleJobRunner implements CommandLineRunner {

    private final JobApplicationService jobApplicationService;

    @Override
    public void run(String... args) {
        jobApplicationService.scheduleJob(CreateJob.builder()
                .name("sampleJob")
                .group("sampleJobGroup")
                .jobClass(JobClass.SAMPLE)
                .cron("0/5 * * * * ?")
                .jobData(Map.of("count", 1))
                .build());

    }
}
