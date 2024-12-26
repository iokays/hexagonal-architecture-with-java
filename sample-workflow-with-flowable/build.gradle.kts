apply(plugin = "org.springframework.boot")
apply(plugin = "io.spring.dependency-management")

dependencies {

    implementation(project(":common-core"))
    implementation(project(":common-domain-with-spring-data-jpa"))

    implementation("commons-validator:commons-validator:1.9.0")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-web")

    //h2
    implementation("com.h2database:h2:2.2.224")

    implementation("org.flowable:flowable-spring-boot-starter:7.1.0")


    implementation("org.mapstruct:mapstruct:1.6.3")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.6.3")

    compileOnly("org.projectlombok:lombok:1.18.34")
    annotationProcessor("org.projectlombok:lombok:1.18.34")

    testImplementation("org.springframework.boot:spring-boot-starter-test")

}
