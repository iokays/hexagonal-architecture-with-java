// 通用模块
include("common-core")
include("common-domain-with-spring-data-jpa")
include("common-domain-with-spring-data-mongodb")
include("common-domain-serialization-with-jackson")
include("common-domain-event-store-with-spring-integration-jdbc")
include("common-domain-event-send-with-spring-integration-jdbc")
include("common-distributed-lock-with-spring-integration")
include("common-distributed-traceid-with-spring")
include("common-distributed-traceid-with-open-telemetry")
include("common-distributed-id-with-snowflake")
include("common-serialization-with-jackson")

// 项目模块
include("iokays-authorizationserver-with-spring-authorization-server-jpa")

// 用例模块
include("sample-spring-integration-jdbc")
include("sample-spring-jdbc")
include("sample-spring-security")
include("sample-spring-authorization-server")