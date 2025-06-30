apply(plugin = "org.springframework.boot")
apply(plugin = "io.spring.dependency-management")

dependencies {

    implementation(project(":common-core"))

    implementation("org.springframework.integration:spring-integration-jdbc")
    implementation("org.springframework.integration:spring-integration-event")

    implementation("com.fasterxml.jackson.core:jackson-annotations")
    implementation("com.fasterxml.jackson.core:jackson-databind")
    implementation("com.fasterxml.jackson.core:jackson-core")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")


    testImplementation("org.postgresql:postgresql:42.7.5")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-starter-jdbc")
    testImplementation("org.springframework.boot:spring-boot-starter-integration")

}
