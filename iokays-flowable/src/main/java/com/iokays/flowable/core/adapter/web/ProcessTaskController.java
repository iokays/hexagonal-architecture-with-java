package com.iokays.flowable.core.adapter.web;

import com.iokays.flowable.core.adapter.web.mapping.ProcessTaskModelMapper;
import com.iokays.flowable.core.adapter.web.model.ProcessTaskModel;
import com.iokays.flowable.core.utils.Pages;
import lombok.AllArgsConstructor;
import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
import org.flowable.task.api.TaskQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/processTasks")
public class ProcessTaskController {

    private final TaskService taskService;
    private final ProcessTaskModelMapper processTaskModelMapper;

    @GetMapping
    public Page<ProcessTaskModel> page() {
        final TaskQuery taskQuery = taskService.createTaskQuery();

        final long count = taskQuery.count();
        if (count == 0) {
            return Page.empty();
        }
        final List<Task> list = taskQuery.list();
        return Pages.toNewPage(Pageable.ofSize((int) count), count, list, processTaskModelMapper::toProcessTaskModel);
    }

    @PutMapping("/{taskId}/complete")
    public void complete(@PathVariable("taskId") final String taskId, @RequestBody Map<String, Object> map) {
        taskService.complete(taskId, map);
    }

}
