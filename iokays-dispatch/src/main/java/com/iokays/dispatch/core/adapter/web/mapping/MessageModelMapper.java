package com.iokays.dispatch.core.adapter.web.mapping;

import com.iokays.dispatch.core.adapter.persistence.message.table.TLocalMessage;
import com.iokays.dispatch.core.adapter.web.model.PageMessageModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(
        componentModel = SPRING
)
public interface MessageModelMapper extends LocalDateTimeMapper {

    @Mapping(source = "messageBytes", target = "content")
    PageMessageModel toPageMessageModel(TLocalMessage model);

    //byte[] to String
    default String bytesToString(final byte[] bytes) {
        return null != bytes ? new String(bytes) : null;
    }

    default LocalDateTime toLocalDateTime(java.sql.Timestamp timestamp) {
        return null != timestamp ? timestamp.toLocalDateTime() : null;
    }

}