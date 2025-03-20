package com.iokays.dispatch.core.application.service;

import com.iokays.common.core.service.ApplicationService;
import com.iokays.dispatch.core.application.service.command.CreateJob;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.quartz.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class JobApplicationService implements ApplicationService {

    private final Scheduler scheduler;

    public void scheduleJob(final CreateJob command) {
        final var job = JobBuilder
                // 任务绑定
                .newJob(command.jobClass().getClazz())
                .storeDurably()
                // 定义唯一标识，区分其他任务
                .withIdentity(command.name(), command.group())
                .usingJobData(new JobDataMap(MapUtils.emptyIfNull(command.jobData())))
                .build();

        final var trigger = TriggerBuilder
                .newTrigger()
                // 唯一表示
                .withIdentity(command.name() + "-trigger", command.group())
                // 绑定JobDetail
                .forJob(job)
                .withSchedule(CronScheduleBuilder.cronSchedule(command.cron()))
                .build();

        Try.run(() -> scheduler.scheduleJob(job, trigger)).onFailure(throwable -> log.error("创建任务失败", throwable));

        log.info("创建任务成功");
    }

    public void pauseJob(final String name, final String group) {
        final JobKey jobKey = Objects.requireNonNull(JobKey.jobKey(name, group), "任务没有找到");
        Try.run(() -> scheduler.pauseJob(jobKey));
    }

    public void deleteJob(final String name, final String group) {
        final JobKey jobKey = Objects.requireNonNull(JobKey.jobKey(name, group), "任务没有找到");
        Try.run(() -> scheduler.deleteJob(jobKey));
    }

    public void resumeJob(final String name, final String group) {
        final JobKey jobKey = Objects.requireNonNull(JobKey.jobKey(name, group), "任务没有找到");
        Try.run(() -> scheduler.resumeJob(jobKey));
    }

    public void triggerJob(final String name, final String group) {
        final JobKey jobKey = Objects.requireNonNull(JobKey.jobKey(name, group), "任务没有找到");
        Try.run(() -> scheduler.triggerJob(jobKey));
    }

    public void rescheduleJob(final String name, final String group, final String cron) {
        //TODO
    }


}