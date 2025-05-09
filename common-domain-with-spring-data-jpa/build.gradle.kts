apply(plugin = "org.springframework.boot")
apply(plugin = "io.spring.dependency-management")

dependencies {
    implementation(project(":common-core"))
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    //h2
    implementation("org.postgresql:postgresql:42.7.5")

    implementation("commons-validator:commons-validator:1.8.0")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}


