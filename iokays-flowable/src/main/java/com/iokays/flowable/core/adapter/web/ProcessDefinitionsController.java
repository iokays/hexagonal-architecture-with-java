package com.iokays.flowable.core.adapter.web;

import com.iokays.flowable.core.adapter.web.mapping.ProcessDefinitionModelMapper;
import com.iokays.flowable.core.adapter.web.model.ProcessDefinitionModel;
import com.iokays.flowable.core.utils.Pages;
import lombok.AllArgsConstructor;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.repository.ProcessDefinition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/processDefinitions")
public class ProcessDefinitionsController {

    private final RepositoryService repositoryService;
    private final ProcessDefinitionModelMapper processDefinitionModelMapper;

    @GetMapping
    public Page<ProcessDefinitionModel> page() {
        final long count = repositoryService.createProcessDefinitionQuery().count();
        if (count == 0) {
            return Page.empty();
        }
        final List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().list();
        return Pages.toNewPage(Pageable.ofSize((int)count), count, list, processDefinitionModelMapper::toProcessDefinitionModel);
    }

}
