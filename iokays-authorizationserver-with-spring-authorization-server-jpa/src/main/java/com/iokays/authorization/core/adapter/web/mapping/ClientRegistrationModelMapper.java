package com.iokays.authorization.core.adapter.web.mapping;

import com.iokays.authorization.core.adapter.web.model.CreateClientRegistrationModel;
import com.iokays.authorization.core.adapter.web.model.PageClientRegistrationModel;
import com.iokays.authorization.core.domain.clientregistration.ClientRegistrationInfo;
import com.iokays.authorization.core.domain.clientregistration.command.CreateClientRegistration;
import com.iokays.common.core.command.CommandId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(
        componentModel = SPRING,
        imports = {CommandId.class},
        uses = {AbstractIdMapper.class}
)
public interface ClientRegistrationModelMapper {

    @Mapping(target = "id", expression = "java( CommandId.generate() )")
    CreateClientRegistration toCreateClientRegistration(CreateClientRegistrationModel model);

    PageClientRegistrationModel toPageClientRegistration(ClientRegistrationInfo info);

}

