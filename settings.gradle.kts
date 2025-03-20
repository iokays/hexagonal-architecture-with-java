// 通用模块
include("common-core")
include("common-domain-with-spring-data-jpa")
include("common-domain-with-spring-data-mongodb")
include("common-domain-serialization-with-jackson")
include("common-domain-event-with-spring-integration-jpa")
include("common-distributed-lock-with-spring-integration")
include("common-distributed-id-with-snowflake")
include("common-serialization-with-jackson")

// 项目模块
include("iokays-authorizationserver-with-spring-authorization-server-jpa") //鉴权
include("iokays-dispatch-with-quartz") //任务
include("iokays-ai-with-spring-ai") //深度学习

// 插件模块
include("iokays-plugin-codegen")
include("iokays-plugin-h2")
