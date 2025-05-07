apply(plugin = "org.springframework.boot")
apply(plugin = "io.spring.dependency-management")

dependencies {

    implementation(project(":common-core"))
    implementation("commons-validator:commons-validator:1.9.0")

    implementation("org.springframework.boot:spring-boot-starter-web")

    implementation("org.springframework.data:spring-data-commons")

    implementation("org.springframework.ai:spring-ai-starter-model-openai:1.0.0-M8")
    implementation("org.springframework.ai:spring-ai-starter-mcp-server:1.0.0-M8")
    implementation("org.springframework.ai:spring-ai-starter-model-chat-memory-jdbc:1.0.0-M8")
    implementation("com.h2database:h2:2.3.232")

    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")

    compileOnly("org.projectlombok:lombok:1.18.34")
    annotationProcessor("org.projectlombok:lombok:1.18.34")

    implementation("org.mapstruct:mapstruct:1.6.3")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.6.3")


    testImplementation("org.springframework.boot:spring-boot-starter-test")

}
