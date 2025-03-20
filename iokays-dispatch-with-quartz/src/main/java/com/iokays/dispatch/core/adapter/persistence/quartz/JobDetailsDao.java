package com.iokays.dispatch.core.adapter.persistence.quartz;

import com.iokays.dispatch.core.adapter.persistence.quartz.table.QQrtzJobDetails;
import com.iokays.dispatch.core.adapter.persistence.quartz.table.QrtzJobDetails;
import com.querydsl.core.types.Projections;
import com.querydsl.sql.SQLQueryFactory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class JobDetailsDao {

    private final SQLQueryFactory sqlQueryFactory;

    public Page<QrtzJobDetails> page(Pageable pageable) {
        final var t = QQrtzJobDetails.qrtzJobDetails;

        //总数
        final var total = sqlQueryFactory.from(t).fetchCount();

        //列表
        final var q = sqlQueryFactory
                .select(Projections.bean(QrtzJobDetails.class, t.schedName, t.jobName, t.jobGroup, t.description, t.jobClassName, t.isDurable, t.isNonconcurrent, t.isUpdateData, t.requestsRecovery))
                .from(t);

        if (pageable.isPaged()) {
            q.offset(pageable.getOffset()).limit(pageable.getPageSize());
        }
        final var result = q.fetch();

        log.info("result: {}", result);

        return new PageImpl<>(result, pageable, total);
    }

}
