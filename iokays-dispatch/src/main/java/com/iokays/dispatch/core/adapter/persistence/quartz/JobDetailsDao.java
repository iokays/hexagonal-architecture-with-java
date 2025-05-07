package com.iokays.dispatch.core.adapter.persistence.quartz;

import com.iokays.dispatch.core.adapter.persistence.quartz.model.JobListModel;
import com.iokays.dispatch.core.adapter.persistence.quartz.table.QQrtzCronTriggers;
import com.iokays.dispatch.core.adapter.persistence.quartz.table.QQrtzJobDetails;
import com.iokays.dispatch.core.adapter.persistence.quartz.table.QQrtzTriggers;
import com.querydsl.core.types.Projections;
import com.querydsl.sql.SQLQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JobDetailsDao {

    private final SQLQueryFactory sqlQueryFactory;

    public JobDetailsDao(@Qualifier("quartzSQLQueryFactory") SQLQueryFactory sqlQueryFactory) {
        this.sqlQueryFactory = sqlQueryFactory;
    }

    public Page<JobListModel> page(Pageable pageable) {
        final var jobDetails = QQrtzJobDetails.qrtzJobDetails;
        final QQrtzTriggers triggers = QQrtzTriggers.qrtzTriggers;
        final QQrtzCronTriggers cronTriggers = QQrtzCronTriggers.qrtzCronTriggers;

        //总数
        final var total = sqlQueryFactory.from(jobDetails).fetchCount();

        //列表
        final var q = sqlQueryFactory
                .select(Projections.bean(JobListModel.class,
                        jobDetails.schedName,
                        jobDetails.jobName,
                        jobDetails.jobGroup,
                        jobDetails.description,
                        jobDetails.jobClassName,
                        jobDetails.isDurable,
                        jobDetails.isNonconcurrent,
                        jobDetails.isUpdateData,
                        jobDetails.requestsRecovery,
                        triggers.startTime,
                        triggers.endTime,
                        cronTriggers.cronExpression
                ))
                .from(jobDetails)
                .innerJoin(triggers).on(jobDetails.schedName.eq(triggers.schedName).and(jobDetails.jobName.eq(triggers.jobName).and(jobDetails.jobGroup.eq(triggers.jobGroup))))
                .leftJoin(cronTriggers).on(triggers.triggerGroup.eq(cronTriggers.triggerGroup).and(triggers.triggerName.eq(cronTriggers.triggerName)));

        if (pageable.isPaged()) {
            q.offset(pageable.getOffset()).limit(pageable.getPageSize());
        }
        final var result = q.fetch();

        log.info("result: {}", result);

        return new PageImpl<>(result, pageable, total);
    }

}
