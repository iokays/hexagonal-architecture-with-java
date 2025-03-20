package com.iokays.dispatch.core.adapter.web;

import com.iokays.common.core.adapter.DriverAdapter;
import com.iokays.dispatch.core.adapter.persistence.quartz.table.QrtzJobDetails;
import com.iokays.dispatch.core.application.service.JobQueryApplicationService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@DriverAdapter
@RestController
@AllArgsConstructor
@RequestMapping("/schedule/jobs")
public class JobController {

    private final JobQueryApplicationService jobQueryApplicationService;

    @GetMapping
    public Page<QrtzJobDetails> page(final Pageable pageable) {
        return jobQueryApplicationService.page(pageable);
    }

}
