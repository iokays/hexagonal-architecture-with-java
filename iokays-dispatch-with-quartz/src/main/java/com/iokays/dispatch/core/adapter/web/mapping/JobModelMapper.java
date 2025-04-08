package com.iokays.dispatch.core.adapter.web.mapping;

import com.google.common.collect.Lists;
import com.iokays.common.core.command.CommandId;
import com.iokays.dispatch.core.adapter.job.JobClass;
import com.iokays.dispatch.core.adapter.persistence.quartz.model.JobListModel;
import com.iokays.dispatch.core.adapter.web.model.CreateJobModel;
import com.iokays.dispatch.core.adapter.web.model.PageJobModel;
import com.iokays.dispatch.core.application.service.command.CreateJob;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(
        componentModel = SPRING,
        imports = {CommandId.class, JobClass.class}
)
public interface JobModelMapper extends LocalDateTimeMapper{

    @Mapping(target = "id", expression = "java( CommandId.generate() )")
    CreateJob toCreateJob(CreateJobModel model);

    @Mapping(target = "actions", source = "model", qualifiedByName = "toActions")
    PageJobModel toPageJobModel(JobListModel model);

    default JobClass toJobClass(String jobClassName) {
        return JobClass.of(jobClassName);
    }

    @Named("toActions")
    default List<String> toActions(JobListModel jobListModel) {
        final List<String> actions = Lists.newArrayList("delete");
        if (jobListModel.getRequestsRecovery()) {
            actions.add("resume");
        } else {
            actions.add("pause");
        }

        return actions;
    }

}