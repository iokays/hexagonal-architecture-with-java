package com.iokays.flowable.core.adapter.web;

import com.iokays.flowable.core.adapter.web.mapping.ProcessInstanceModelMapper;
import com.iokays.flowable.core.adapter.web.model.ProcessInstanceModel;
import com.iokays.flowable.core.utils.Pages;
import lombok.AllArgsConstructor;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.engine.runtime.ProcessInstanceQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/processInstances")
public class ProcessInstanceController {

    private final RuntimeService runtimeService;
    private final ProcessInstanceModelMapper processInstanceModelMapper;

    @GetMapping
    public Page<ProcessInstanceModel> page() {
        final ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery();

        final long count = processInstanceQuery.count();
        if (count == 0) {
            return Page.empty();
        }
        final List<ProcessInstance> list = processInstanceQuery.list();
        return Pages.toNewPage(Pageable.ofSize((int)count), count, list, processInstanceModelMapper::toProcessInstanceModel);
    }

}
