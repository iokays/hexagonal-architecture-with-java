package com.iokays.dispatch.core.adapter.web;

import com.iokays.common.core.adapter.DriverAdapter;
import com.iokays.dispatch.core.adapter.utils.Pages;
import com.iokays.dispatch.core.adapter.web.mapping.JobModelMapper;
import com.iokays.dispatch.core.adapter.web.mapping.MessageModelMapper;
import com.iokays.dispatch.core.adapter.web.model.CreateJobModel;
import com.iokays.dispatch.core.adapter.web.model.PageJobModel;
import com.iokays.dispatch.core.adapter.web.model.PageMessageModel;
import com.iokays.dispatch.core.adapter.web.model.QueryMessageModel;
import com.iokays.dispatch.core.application.service.JobApplicationService;
import com.iokays.dispatch.core.application.service.JobQueryApplicationService;
import com.iokays.dispatch.core.application.service.MessageQueryApplicationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@Slf4j
@DriverAdapter
@RestController
@AllArgsConstructor
@RequestMapping("/schedule/messages")
public class MessageController {

    private final MessageQueryApplicationService messageQueryApplicationService;

    private final MessageModelMapper messageModelMapper;

    @GetMapping
    public Page<PageMessageModel> page(final QueryMessageModel query, final Pageable pageable) {
        log.info("query: {}", query);
        return Pages.toNewPage(pageable, messageQueryApplicationService.page(query.getCategory(), pageable), messageModelMapper::toPageMessageModel);
    }

}
