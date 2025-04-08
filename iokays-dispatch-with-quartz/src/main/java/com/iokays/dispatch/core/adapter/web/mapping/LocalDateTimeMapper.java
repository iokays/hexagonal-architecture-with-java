package com.iokays.dispatch.core.adapter.web.mapping;

import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public interface LocalDateTimeMapper {

    // long to LocalDateTime
    default LocalDateTime toLocalDateTime(Long timestamp) {
        if (timestamp == null) {
            return null;
        }
        // 转换为 Instant（UTC 时间）
        Instant instant = Instant.ofEpochMilli(timestamp);
        // 转换为北京时间（GMT+8）
        return LocalDateTime.ofInstant(instant, ZoneId.of("GMT+8"));
    }

}