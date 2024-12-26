import org.springframework.boot.gradle.plugin.SpringBootPlugin

apply(plugin = "org.springframework.boot")
apply(plugin = "io.spring.dependency-management")

dependencies {
    implementation(platform(SpringBootPlugin.BOM_COORDINATES))
    implementation(platform("io.opentelemetry.instrumentation:opentelemetry-instrumentation-bom:2.9.0"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("io.opentelemetry.instrumentation:opentelemetry-spring-boot-starter")
    implementation("io.opentelemetry.instrumentation:opentelemetry-logback-mdc-1.0:2.9.0-alpha")
    implementation("io.opentelemetry.contrib:opentelemetry-samplers:1.33.0-alpha")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}
