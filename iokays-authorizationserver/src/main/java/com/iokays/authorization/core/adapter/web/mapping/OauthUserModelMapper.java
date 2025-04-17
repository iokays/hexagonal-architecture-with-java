package com.iokays.authorization.core.adapter.web.mapping;

import com.iokays.authorization.core.adapter.web.model.PageOauthUserModel;
import com.iokays.authorization.core.domain.oauth2user.OauthUserInfo;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(
        componentModel = SPRING,
        uses = {AbstractIdMapper.class}
)
public interface OauthUserModelMapper {

    PageOauthUserModel toOauthUserPageModel(OauthUserInfo user);

}
