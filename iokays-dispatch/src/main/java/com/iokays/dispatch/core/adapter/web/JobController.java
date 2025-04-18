package com.iokays.dispatch.core.adapter.web;

import com.iokays.common.core.adapter.DriverAdapter;
import com.iokays.dispatch.core.adapter.utils.Pages;
import com.iokays.dispatch.core.adapter.web.mapping.JobModelMapper;
import com.iokays.dispatch.core.adapter.web.model.CreateJobModel;
import com.iokays.dispatch.core.adapter.web.model.PageJobModel;
import com.iokays.dispatch.core.adapter.web.model.QueryMessageModel;
import com.iokays.dispatch.core.application.service.JobApplicationService;
import com.iokays.dispatch.core.application.service.JobQueryApplicationService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@DriverAdapter
@RestController
@AllArgsConstructor
@RequestMapping("/schedule/jobs")
public class JobController {

    private final JobQueryApplicationService jobQueryApplicationService;

    private final JobApplicationService jobApplicationService;

    private final JobModelMapper jobModelMapper;

    @GetMapping
    public Page<PageJobModel> page(final Pageable pageable) {
        return Pages.toNewPage(pageable, jobQueryApplicationService.page(pageable), jobModelMapper::toPageJobModel);
    }

    @PostMapping
    public void scheduleJob(@RequestBody CreateJobModel job) {
        jobApplicationService.scheduleJob(jobModelMapper.toCreateJob(job));
    }

    @PatchMapping("/{jobGroup}/{jobName}/pause")
    public void pauseJob(@PathVariable String jobGroup, @PathVariable String jobName) {
        jobApplicationService.pauseJob(jobName, jobGroup);
    }

    @PatchMapping("/{jobGroup}/{jobName}/resume")
    public void resumeJob(@PathVariable String jobGroup, @PathVariable String jobName) {
        jobApplicationService.resumeJob(jobName, jobGroup);
    }

    @PatchMapping("/{jobGroup}/{jobName}/trigger")
    public void triggerJob(@PathVariable String jobGroup, @PathVariable String jobName) {
        jobApplicationService.triggerJob(jobName, jobGroup);
    }

    @DeleteMapping("/{jobGroup}/{jobName}/delete")
    public void delete(@PathVariable String jobGroup, @PathVariable String jobName) {
        jobApplicationService.deleteJob(jobName, jobGroup);
    }

}
