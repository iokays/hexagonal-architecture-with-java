package com.iokays.flowable.core.adapter.web;

import com.iokays.flowable.core.adapter.web.mapping.ProcessDefinitionModelMapper;
import com.iokays.flowable.core.adapter.web.model.CreateProcessDefinitionModel;
import com.iokays.flowable.core.adapter.web.model.ProcessDefinitionModel;
import com.iokays.flowable.core.utils.Pages;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Slf4j
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
        final List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery()
                .latestVersion()
                .list();
        return Pages.toNewPage(Pageable.ofSize((int)count), count, list, processDefinitionModelMapper::toProcessDefinitionModel);
    }

    @GetMapping("/{processDefinitionId}/processModel")
    public byte[] processModel(@PathVariable("processDefinitionId") String processDefinitionId) throws IOException {
        final InputStream processModel = repositoryService.getProcessModel(processDefinitionId);
        return IOUtils.toByteArray(processModel);
    }

    @PostMapping
    public String deployment(@RequestBody CreateProcessDefinitionModel model) {
        return repositoryService.createDeployment().
                addBytes(model.resourceName(), model.bytes())
                .deploy()
                .getParentDeploymentId();
    }

    @PostMapping("upload")
    public void upload(@RequestParam("file")MultipartFile file) throws IOException {
        log.info("upload file: {}", file.getOriginalFilename());
        final var deployment = repositoryService.createDeployment()
                .name("Deployment from " + file.getOriginalFilename()) // 添加部署名称
                .addInputStream(file.getOriginalFilename(), file.getInputStream())
                .deploy();

        log.info("Deployment ID: {}, Deployment time: {}",
                deployment.getId(), deployment.getDeploymentTime());
    }
}
