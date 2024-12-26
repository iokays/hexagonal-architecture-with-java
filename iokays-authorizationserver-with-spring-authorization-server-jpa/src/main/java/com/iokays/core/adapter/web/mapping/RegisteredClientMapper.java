package com.iokays.core.adapter.web.mapping;

import com.iokays.common.core.command.CommandId;
import com.iokays.core.adapter.web.model.CreateRegisteredClientModel;
import com.iokays.core.domain.registeredclient.commond.RegisterClient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(
        imports = {CommandId.class},
        componentModel = SPRING
)
public interface RegisteredClientMapper {

    RegisteredClientMapper INSTANCE = Mappers.getMapper(RegisteredClientMapper.class);

    @Mapping(target = "id", expression = "java( CommandId.generate() )")
    RegisterClient toRegisterClient(CreateRegisteredClientModel registeredClient);
}
