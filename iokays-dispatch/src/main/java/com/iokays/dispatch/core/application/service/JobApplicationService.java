package com.iokays.dispatch.core.application.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iokays.common.core.error.ApplicationServiceUnKnowException;
import com.iokays.common.core.service.ApplicationService;
import com.iokays.dispatch.core.adapter.utils.JobUtils;
import com.iokays.dispatch.core.application.service.command.CreateJob;
import com.iokays.dispatch.core.application.service.exception.JobAlreadyExistsApplicationServiceException;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class JobApplicationService implements ApplicationService {

    private final Scheduler scheduler;
    private final ObjectMapper objectMapper;

    public void scheduleJob(final CreateJob command) {
        final var job = JobBuilder
                // 任务绑定
                .newJob(command.jobClass().getClazz())
                .storeDurably()
                // 定义唯一标识，区分其他任务
                .withIdentity(command.name(), command.group())
                .usingJobData(JobUtils.INPUT_DATA, JobUtils.toJson(command.input()))
                .build();

        final var trigger = TriggerBuilder
                .newTrigger()
                // 唯一表示
                .withIdentity(command.name() + "-trigger", command.group())
                // 绑定JobDetail
                .forJob(job.getKey())
                .withSchedule(CronScheduleBuilder.cronSchedule(command.cronExpression()));

        if (Objects.nonNull(command.startAt())) {
            trigger.startNow();
        }

        if (Objects.nonNull(command.endAt())) {
            trigger.endAt(toDate(command.endAt()));
        }

        Try.run(() -> scheduler.scheduleJob(job, trigger.build())).onFailure(e -> {
            if (e instanceof ObjectAlreadyExistsException) {
                throw new JobAlreadyExistsApplicationServiceException(job);
            }
            throw new ApplicationServiceUnKnowException("创建任务失败", e);
        });

        log.info("创建任务成功");
    }

    private Date toDate(final LocalDateTime dateTime) {
        return java.sql.Timestamp.valueOf(Objects.requireNonNull(dateTime, "时间不能为空"));
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