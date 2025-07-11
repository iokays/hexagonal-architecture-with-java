apply(plugin = "org.springframework.boot")
apply(plugin = "io.spring.dependency-management")
apply(plugin = "org.jetbrains.kotlin.jvm") //kotlin
apply(plugin = "org.jetbrains.kotlin.plugin.spring") //kotlin-spring

dependencies {

    implementation(project(":common-core"))
    implementation(project(":common-domain-with-spring-data-jpa"))
    implementation(project(":common-domain-event-with-spring-integration-jdbc"))

    implementation("commons-validator:commons-validator:1.9.0")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")

    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
    implementation("org.springframework.security:spring-security-oauth2-authorization-server")
    implementation("com.nimbusds:nimbus-jose-jwt:9.37.3")
    implementation("org.springframework.session:spring-session-jdbc")

    implementation("org.springframework.integration:spring-integration-event")
    implementation("org.springframework.integration:spring-integration-jdbc")
    implementation("com.querydsl:querydsl-sql-spring:5.1.0")

    implementation("org.postgresql:postgresql:42.7.5")

    implementation("org.bitbucket.b_c:jose4j:0.9.6")

    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")

    compileOnly("org.projectlombok:lombok:1.18.34")
    annotationProcessor("org.projectlombok:lombok:1.18.34")

    implementation("org.mapstruct:mapstruct:1.6.3")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.6.3")

    // 追踪
//    implementation("org.springframework.boot:spring-boot-starter-actuator")
//    implementation("io.micrometer:micrometer-tracing-bridge-otel:1.4.1")
    // 配置 zipkin.
//    implementation("io.opentelemetry:opentelemetry-exporter-zipkin:1.45.0")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("com.jayway.jsonpath:json-path:2.9.0")

}
