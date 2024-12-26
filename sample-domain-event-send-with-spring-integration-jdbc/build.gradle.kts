apply(plugin = "org.springframework.boot")
apply(plugin = "io.spring.dependency-management")

dependencies {
    implementation(project(":common-core"))

    implementation("com.fasterxml.jackson.core:jackson-annotations")
    implementation("com.fasterxml.jackson.core:jackson-databind")
    implementation("com.fasterxml.jackson.core:jackson-core")

    implementation("org.springframework.boot:spring-boot-starter-jdbc")

    implementation("org.springframework.boot:spring-boot-starter-integration")
    implementation("org.springframework.integration:spring-integration-jdbc")
    implementation("org.springframework.integration:spring-integration-event")

    testImplementation("mysql:mysql-connector-java:8.0.28")
    testImplementation("com.google.protobuf:protobuf-java:3.16.3")

    implementation("com.h2database:h2:2.2.224")

    testImplementation("org.springframework.boot:spring-boot-starter-test")

}
