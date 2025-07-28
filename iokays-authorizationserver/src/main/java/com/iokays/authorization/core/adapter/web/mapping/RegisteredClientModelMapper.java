package com.iokays.authorization.core.adapter.web.mapping;

import com.iokays.authorization.core.adapter.web.model.CreateRegisteredClientModel;
import com.iokays.authorization.core.adapter.web.model.PageRegisteredClientModel;
import com.iokays.authorization.core.domain.registeredclient.RegisteredClientInfo;
import com.iokays.authorization.core.domain.registeredclient.commond.RegisterClient;
import com.iokays.common.core.command.CommandId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(
        componentModel = SPRING,
        imports = {CommandId.class},
        uses = {AbstractCodeMapper.class}
)
public interface RegisteredClientModelMapper {

    @Mapping(target = "id", expression = "java( CommandId.generate() )")
    RegisterClient toRegisterClient(CreateRegisteredClientModel registeredClient);

    @Mapping(target = "actions", source = "info", qualifiedByName = "toActions")
    PageRegisteredClientModel toPageRegisteredClientModel(RegisteredClientInfo info);

    @Named("toActions")
    default Set<String> toActions(RegisteredClientInfo info) {
        return Set.of("delete");
    }
}
