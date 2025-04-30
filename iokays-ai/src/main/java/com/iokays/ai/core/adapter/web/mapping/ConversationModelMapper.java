package com.iokays.ai.core.adapter.web.mapping;

import com.iokays.ai.core.adapter.web.SecurityUserContext;
import com.iokays.ai.core.adapter.web.model.ConversationModel;
import com.iokays.ai.core.adapter.web.model.CreateConversationModel;
import com.iokays.ai.core.domain.conversation.command.CreateConversation;
import com.iokays.common.core.command.CommandId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(
        componentModel = SPRING,
        imports = {CommandId.class, SecurityUserContext.class},
        uses = {AbstractIdMapper.class}
)
public interface ConversationModelMapper {

    @Mapping(target = "id", expression = "java( CommandId.generate() )")
    @Mapping(target = "authenticatedUser", expression = "java( SecurityUserContext.authenticatedUser() )")
    CreateConversation toCreateConversation(CreateConversationModel model);

}
