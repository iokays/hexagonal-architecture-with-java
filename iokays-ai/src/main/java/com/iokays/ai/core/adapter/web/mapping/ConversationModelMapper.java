package com.iokays.ai.core.adapter.web.mapping;

import com.iokays.ai.core.adapter.web.SecurityUserContext;
import com.iokays.common.core.command.CommandId;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(
        componentModel = SPRING,
        imports = {CommandId.class, SecurityUserContext.class}
)
public interface ConversationModelMapper {

}
