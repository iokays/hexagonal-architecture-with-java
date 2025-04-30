package com.iokays.ai.core.adapter.web.mapping;

import com.iokays.common.domain.jpa.AbstractId;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface AbstractIdMapper {

    default String id(final AbstractId id) {
        return id != null ? id.id() : null;
    }
}
