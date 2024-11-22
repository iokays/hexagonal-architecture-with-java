apply(plugin = "org.springframework.boot")
apply(plugin = "io.spring.dependency-management")

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.bitbucket.b_c:jose4j:0.9.6")
    implementation("org.springframework.security:spring-security-oauth2-authorization-server")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}
