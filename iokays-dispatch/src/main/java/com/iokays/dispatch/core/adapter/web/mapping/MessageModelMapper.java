package com.iokays.dispatch.core.adapter.web.mapping;

import com.iokays.dispatch.core.adapter.persistence.message.table.ChannelMessage;
import com.iokays.dispatch.core.adapter.web.model.PageMessageModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(
        componentModel = SPRING
)
public interface MessageModelMapper extends LocalDateTimeMapper {

    @Mapping(source = "messageBytes", target = "content")
    PageMessageModel toPageMessageModel(ChannelMessage model);

    //byte[] to String
    default String bytesToString(final byte[] bytes) {
        return null != bytes ? new String(bytes) : null;
    }

    default LocalDateTime toLocalDateTime(Long createdDate) {
        if (createdDate == null) {
            return null;
        }

        // 假设 createdDate 是毫秒时间戳（如果是秒时间戳，需要 *1000）
        final Instant instant = Instant.ofEpochMilli(createdDate);
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

}