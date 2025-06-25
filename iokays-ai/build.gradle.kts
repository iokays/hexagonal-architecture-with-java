apply(plugin = "org.springframework.boot")
apply(plugin = "io.spring.dependency-management")

dependencies {

    implementation(project(":common-core"))
    implementation("commons-validator:commons-validator:1.9.0")

    implementation("org.springframework.boot:spring-boot-starter-webflux")

    implementation("org.springframework.data:spring-data-commons")

    implementation("org.springframework.ai:spring-ai-starter-model-openai:1.0.0")
    implementation("org.springframework.ai:spring-ai-starter-model-chat-memory-repository-jdbc:1.0.0")
    implementation("org.springframework.ai:spring-ai-starter-vector-store-pgvector:1.0.0")
    implementation("org.postgresql:postgresql:42.7.5")

    implementation("org.springframework.ai:spring-ai-starter-mcp-client-webflux:1.0.0")
//    implementation("io.netty:netty-resolver-dns-native-macos:4.2.2.Final:osx-aarch_64")

    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")

    compileOnly("org.projectlombok:lombok:1.18.34")
    annotationProcessor("org.projectlombok:lombok:1.18.34")

    implementation("org.mapstruct:mapstruct:1.6.3")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.6.3")


    testImplementation("org.springframework.boot:spring-boot-starter-test")

}
