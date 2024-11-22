apply(plugin = "org.springframework.boot")
apply(plugin = "io.spring.dependency-management")

dependencies {
    implementation(project(":common-core"))
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    //h2
    testImplementation("com.h2database:h2:2.2.224")
    testImplementation("commons-validator:commons-validator:1.8.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}


