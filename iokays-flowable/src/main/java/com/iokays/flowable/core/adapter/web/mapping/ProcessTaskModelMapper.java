package com.iokays.flowable.core.adapter.web.mapping;

import com.iokays.flowable.core.adapter.web.model.ProcessTaskModel;
import org.flowable.task.api.Task;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(
        componentModel = SPRING
)
public interface ProcessTaskModelMapper {

    ProcessTaskModel toProcessTaskModel(Task task);
}
