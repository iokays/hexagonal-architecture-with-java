package com.iokays.authorization.core.adapter.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public record PageGroupModel(

        String groupId,

        String groupName,

        List<String> authorities,
        /**
         * 创建时间，表示记录的创建时间戳。
         */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        LocalDateTime createdDate,

        /**
         * 最后修改时间，表示记录的最后修改时间戳。
         */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        LocalDateTime lastModifiedDate
) implements Serializable {
}
