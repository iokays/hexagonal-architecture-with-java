// Root Project中build.gradle
plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "2.1.20" apply (false)
    id("org.jetbrains.kotlin.plugin.spring") version "2.1.20" apply (false)
    id("org.springframework.boot") version "3.4.4" apply (false)
    id("io.spring.dependency-management") version "1.1.7" apply (false)
}

allprojects {

    // 确保 java 插件被正确应用
    apply(plugin = "java")

    java {
        sourceCompatibility = JavaVersion.VERSION_23
        targetCompatibility = JavaVersion.VERSION_23
    }

    tasks.withType<JavaCompile>().configureEach {
        options.compilerArgs.add("--enable-preview")
    }

    tasks.withType<JavaExec>().configureEach {
        jvmArgs("--enable-preview")
    }

    tasks.test {
        useJUnitPlatform()
        jvmArgs("--enable-preview")
    }

    dependencies {

        implementation(platform("com.fasterxml.jackson:jackson-bom:2.18.2"))
        implementation(platform("com.google.guava:guava-bom:33.3.1-jre"))
        implementation(platform("org.slf4j:slf4j-bom:2.1.0-alpha1"))

        implementation("io.vavr:vavr:0.10.4")
        implementation("com.google.guava:guava")
        implementation("org.apache.commons:commons-collections4:4.4")
        implementation("org.apache.commons:commons-lang3:3.14.0")
        implementation("commons-codec:commons-codec:1.16.0")
        implementation("org.jasypt:jasypt:1.9.3")
        implementation("org.slf4j:slf4j-api")
        implementation("ch.qos.logback:logback-core:1.5.13")
        implementation("ch.qos.logback:logback-classic:1.5.13")
        implementation("io.swagger.core.v3:swagger-annotations:2.2.21")

        testImplementation("org.junit.jupiter:junit-jupiter:5.11.3")
        testImplementation("net.datafaker:datafaker:2.4.1")

        testCompileOnly("org.projectlombok:lombok:1.18.34")
        testAnnotationProcessor("org.projectlombok:lombok:1.18.34")
    }

    repositories {
        mavenLocal()
        maven { url = uri("https://maven.aliyun.com/nexus/content/groups/public") }
        maven { url = uri("https://repo.maven.apache.org/maven2") }
        maven { url = uri("https://repository.jboss.org/nexus/content/groups/public-jboss") }
        maven { url = uri("https://repo.spring.io/milestone") }
        maven { url = uri("https://repo.spring.io/snapshot") }
        google()
        mavenCentral()
    }

}

