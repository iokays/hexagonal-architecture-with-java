package com.iokays.authorization.core.adapter.web.mapping;

import com.iokays.authorization.core.adapter.web.model.CreateClientRegistrationModel;
import com.iokays.authorization.core.adapter.web.model.PageClientRegistrationModel;
import com.iokays.authorization.core.domain.clientregistration.ClientRegistrationInfo;
import com.iokays.authorization.core.domain.clientregistration.command.CreateClientRegistration;
import com.iokays.common.core.command.CommandId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(
        componentModel = SPRING,
        imports = {CommandId.class},
        uses = {AbstractIdMapper.class}
)
public interface ClientRegistrationModelMapper {

    @Mapping(target = "id", expression = "java( CommandId.generate() )")
    @Mapping(target = "redirectUri", constant = "{baseUrl}/login/oauth2/code/{registrationId}")
    CreateClientRegistration toCreateClientRegistration(CreateClientRegistrationModel model);

    @Mapping(target = "actions", source = "info", qualifiedByName = "toActions")
    PageClientRegistrationModel toPageClientRegistration(ClientRegistrationInfo info);

    @Named("toActions")
    default Set<String> toActions(ClientRegistrationInfo info) {
        return Set.of("delete");
    }

}

