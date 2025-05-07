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
include("iokays-ai") //AI
include("iokays-authorizationserver") //鉴权
include("iokays-dispatch") //任务
include("iokays-deeplearning") //深度学习
include("iokays-webflux")

// 插件模块
include("iokays-plugin-codegen")
include("iokays-plugin-h2")