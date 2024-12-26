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
include("iokays-authorizationserver-with-spring-authorization-server-jpa")

// 插件模块
include("iokays-plugin-codegen")

// 用例模块
include("sample-spring-integration-jdbc")
include("sample-spring-jdbc")
include("sample-spring-security")
include("sample-spring-authorization-server")

include("sample-domain-event-store-with-spring-integration-jdbc")
include("sample-domain-event-send-with-spring-integration-jdbc")
include("sample-distributed-traceid-with-open-telemetry")
include("sample-distributed-traceid-with-spring")
include("sample-workflow-with-flowable")
