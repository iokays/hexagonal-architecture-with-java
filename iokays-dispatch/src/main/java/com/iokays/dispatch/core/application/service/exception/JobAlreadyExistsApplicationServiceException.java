package com.iokays.dispatch.core.application.service.exception;

import com.iokays.common.core.error.ApplicationServiceException;
import org.quartz.JobDetail;

public class JobAlreadyExistsApplicationServiceException extends ApplicationServiceException {

    public JobAlreadyExistsApplicationServiceException(final JobDetail jobDetail) {
        super("任务已存在: " + jobDetail.getKey());
    }
}
