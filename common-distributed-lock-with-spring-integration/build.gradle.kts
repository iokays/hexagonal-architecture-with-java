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

    implementation("org.aspectj:aspectjweaver:+")
    implementation("org.springframework:spring-core")
    implementation("org.springframework:spring-aop")
    implementation("org.springframework.integration:spring-integration-core")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}


