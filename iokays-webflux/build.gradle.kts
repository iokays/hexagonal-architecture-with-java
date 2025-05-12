apply(plugin = "org.springframework.boot")
apply(plugin = "io.spring.dependency-management")

dependencies {

    implementation(project(":common-core"))
    implementation("commons-validator:commons-validator:1.9.0")

    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    implementation("org.postgresql:r2dbc-postgresql:1.0.7.RELEASE")

    implementation("org.springframework.boot:spring-boot-starter-webflux")

    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.session:spring-session-core")

    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")

    compileOnly("org.projectlombok:lombok:1.18.34")
    annotationProcessor("org.projectlombok:lombok:1.18.34")

    implementation("org.mapstruct:mapstruct:1.6.3")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.6.3")


    testImplementation("org.springframework.boot:spring-boot-starter-test")

}
