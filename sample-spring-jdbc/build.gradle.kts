apply(plugin = "org.springframework.boot")
apply(plugin = "io.spring.dependency-management")

dependencies {

    implementation("com.fasterxml.jackson.core:jackson-annotations")
    implementation("com.fasterxml.jackson.core:jackson-databind")
    implementation("com.fasterxml.jackson.core:jackson-core")

    implementation("org.springframework.boot:spring-boot-starter-jdbc")

    testImplementation("org.postgresql:postgresql:42.7.4")
    testImplementation("mysql:mysql-connector-java:8.0.28")

    testImplementation("com.google.protobuf:protobuf-java:4.29.0-RC2")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("net.datafaker:datafaker:2.4.1")

}
