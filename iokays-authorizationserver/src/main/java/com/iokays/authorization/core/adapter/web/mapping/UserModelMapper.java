package com.iokays.authorization.core.adapter.web.mapping;

import com.iokays.authorization.core.adapter.persistence.querydsl.table.TUsers;
import com.iokays.authorization.core.adapter.web.model.PageUserModel;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(
        componentModel = SPRING
)
public interface UserModelMapper {

    PageUserModel toUserPageModel(TUsers users);


}
