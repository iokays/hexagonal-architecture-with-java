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
    implementation(project(":common-domain-with-spring-data-jpa"))
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.mvel:mvel2:2.5.2.Final")

    testImplementation("org.postgresql:postgresql:42.7.5")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}


