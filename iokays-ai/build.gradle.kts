
apply(plugin = "org.springframework.boot")
apply(plugin = "io.spring.dependency-management")

dependencies {

    implementation(project(":common-core"))
    implementation("commons-validator:commons-validator:1.9.0")

    implementation(project(":common-domain-with-spring-data-jpa"))
    implementation(project(":common-domain-event-with-spring-integration-jpa"))
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    implementation("org.springframework.ai:spring-ai-starter-model-openai:1.0.0-M7")
    implementation("org.springframework.ai:spring-ai-starter-mcp-server-webflux:1.0.0-M7")

    implementation ("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.session:spring-session-jdbc")

    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")

    compileOnly("org.projectlombok:lombok:1.18.34")
    annotationProcessor("org.projectlombok:lombok:1.18.34")

    implementation("org.mapstruct:mapstruct:1.6.3")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.6.3")


    testImplementation("org.springframework.boot:spring-boot-starter-test")

}
