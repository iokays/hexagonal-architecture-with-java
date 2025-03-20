package com.iokays.dispatch.core.application.service;

import com.iokays.common.core.service.ApplicationService;
import com.iokays.dispatch.core.adapter.persistence.quartz.JobDetailsDao;
import com.iokays.dispatch.core.adapter.persistence.quartz.table.QrtzJobDetails;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class JobQueryApplicationService implements ApplicationService {

    private final JobDetailsDao jobDetailsDao;

    public Page<QrtzJobDetails> page(Pageable pageable) {
        return jobDetailsDao.page(pageable);
    }

}
