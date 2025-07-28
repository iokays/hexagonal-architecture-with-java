package com.iokays.authorization.core.adapter.web.mapping;

import com.iokays.common.domain.jpa.AbstractCode;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface AbstractCodeMapper {

    default String code(final AbstractCode code) {
        return code != null ? code.code() : null;
    }
}
