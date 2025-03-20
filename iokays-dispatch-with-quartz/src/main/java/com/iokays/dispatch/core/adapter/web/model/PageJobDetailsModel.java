package com.iokays.dispatch.core.adapter.web.model;

public record PageJobDetailsModel(
        /**
         * 描述任务的详细信息
         */
        String description,

        /**
         * 标识任务是否持久化。如果为true，任务在调度程序关闭后仍然存在。
         */
        Boolean isDurable,

        /**
         * 标识任务是否不允许并发执行。如果为true，同一任务的多个实例不能同时运行。
         */
        Boolean isNonconcurrent,

        /**
         * 标识任务是否更新数据。如果为true，任务在执行时会更新相关数据。
         */
        Boolean isUpdateData,

        /**
         * 任务类的全限定名，指定了任务的具体实现类。
         */
        String jobClassName,

        /**
         * 任务的二进制数据，通常用于存储任务的配置或状态信息。
         */
        java.sql.Blob jobData,

        /**
         * 任务所属的组名，用于对任务进行分类或分组。
         */
        String jobGroup,

        /**
         * 任务的名称，用于唯一标识任务。
         */
        String jobName,

        /**
         * 标识任务是否需要恢复。如果为true，任务在调度程序崩溃后会被重新执行。
         */
        Boolean requestsRecovery,

        /**
         * 调度程序的名称，用于标识任务所属的调度程序。
         */
        String schedName
) {
}
