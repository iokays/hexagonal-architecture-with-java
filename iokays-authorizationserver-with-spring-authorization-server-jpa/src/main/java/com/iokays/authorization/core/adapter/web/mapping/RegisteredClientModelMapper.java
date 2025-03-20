package com.iokays.authorization.core.adapter.web.mapping;

import com.iokays.authorization.core.adapter.web.model.CreateRegisteredClientModel;
import com.iokays.authorization.core.adapter.web.model.PageRegisteredClientModel;
import com.iokays.authorization.core.domain.registeredclient.RegisteredClientInfo;
import com.iokays.authorization.core.domain.registeredclient.commond.RegisterClient;
import com.iokays.common.core.command.CommandId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(
        componentModel = SPRING,
        imports = {CommandId.class},
        uses = {AbstractIdMapper.class}
)
public interface RegisteredClientModelMapper {

    @Mapping(target = "id", expression = "java( CommandId.generate() )")
    RegisterClient toRegisterClient(CreateRegisteredClientModel registeredClient);

    PageRegisteredClientModel toPageRegisteredClientModel(RegisteredClientInfo info);
}
