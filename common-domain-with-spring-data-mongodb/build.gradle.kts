apply(plugin = "org.springframework.boot")
apply(plugin = "io.spring.dependency-management")

// 配置为库模块而非可执行应用
tasks.getByName<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    enabled = false
}

tasks.getByName<Jar>("jar") {
    enabled = true
}

dependencies {
    implementation(project(":common-core"))
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")

    testImplementation("commons-validator:commons-validator:1.8.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}


