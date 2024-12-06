apply(plugin = "org.springframework.boot")
apply(plugin = "io.spring.dependency-management")
apply(plugin = "org.jetbrains.kotlin.jvm") //kotlin
apply(plugin = "org.jetbrains.kotlin.plugin.spring") //kotlin-spring

dependencies {
    implementation(project(":common-serialization-with-jackson"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("net.datafaker:datafaker:2.4.1")
}
