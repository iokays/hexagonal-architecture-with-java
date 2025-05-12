package com.iokays.flowable.core.adapter.web.mapping;

import com.iokays.flowable.core.adapter.web.model.ProcessInstanceModel;
import org.flowable.engine.runtime.ProcessInstance;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(
        componentModel = SPRING
)
public interface ProcessInstanceModelMapper {

    ProcessInstanceModel toProcessInstanceModel(ProcessInstance processInstance);
}
