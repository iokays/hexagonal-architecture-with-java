package com.iokays.dispatch.core.adapter.web.model;

public record DeleteJobDetailsModel(
        /**
         * 任务所属的组名，用于对任务进行分类或分组。
         */
        String jobGroup,

        /**
         * 任务的名称，用于唯一标识任务。
         */
        String jobName
) {
}
