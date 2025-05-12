package com.iokays.flowable.core.adapter.web.mapping;

import com.iokays.flowable.core.adapter.web.model.ProcessDefinitionModel;
import org.flowable.engine.repository.ProcessDefinition;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(
        componentModel = SPRING
)
public interface ProcessDefinitionModelMapper {

    ProcessDefinitionModel toProcessDefinitionModel(ProcessDefinition processDefinition);
}
