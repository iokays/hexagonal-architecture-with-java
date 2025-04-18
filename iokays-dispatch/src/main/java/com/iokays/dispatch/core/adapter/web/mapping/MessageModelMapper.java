package com.iokays.dispatch.core.adapter.web.mapping;

import com.google.common.collect.Sets;
import com.iokays.common.core.command.CommandId;
import com.iokays.dispatch.core.adapter.job.JobClass;
import com.iokays.dispatch.core.adapter.persistence.message.table.TLocalMessage;
import com.iokays.dispatch.core.adapter.persistence.quartz.model.JobListModel;
import com.iokays.dispatch.core.adapter.web.model.CreateJobModel;
import com.iokays.dispatch.core.adapter.web.model.PageJobModel;
import com.iokays.dispatch.core.adapter.web.model.PageMessageModel;
import com.iokays.dispatch.core.application.service.command.CreateJob;
import com.mchange.lang.ByteUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.util.Set;

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