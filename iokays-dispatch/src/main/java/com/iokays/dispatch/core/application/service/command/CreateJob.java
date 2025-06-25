package com.iokays.dispatch.core.application.service.command;

import com.iokays.common.core.command.Command;
import com.iokays.common.core.command.CommandId;
import com.iokays.dispatch.core.adapter.job.JobClass;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Map;

@Builder
public record CreateJob(
        CommandId id,
        String name,
        String group,
        String cronExpression,
        LocalDateTime startAt,
        LocalDateTime endAt,
        JobClass jobClass,
        Object input
) implements Command {
}
