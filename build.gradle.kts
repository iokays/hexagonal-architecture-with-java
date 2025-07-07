// Root Project中build.gradle
plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "2.1.20" apply (false)
    id("org.jetbrains.kotlin.plugin.spring") version "2.1.20" apply (false)
    id("org.springframework.boot") version "3.5.3" apply (false)
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

        implementation(platform("com.fasterxml.jackson:jackson-bom:+"))
        implementation(platform("com.google.guava:guava-bom:+"))
        implementation(platform("org.slf4j:slf4j-bom:+"))

        implementation("io.vavr:vavr:0.10.4")
        implementation("com.google.guava:guava")
        implementation("org.apache.commons:commons-collections4:+")
        implementation("org.apache.commons:commons-lang3:+")
        implementation("commons-codec:commons-codec:+")
        implementation("org.jasypt:jasypt:+")
        implementation("org.slf4j:slf4j-api")
        implementation("ch.qos.logback:logback-core:+")
        implementation("ch.qos.logback:logback-classic:+")
        implementation("io.swagger.core.v3:swagger-annotations:+")

        testImplementation("org.junit.jupiter:junit-jupiter:+")
        testImplementation("net.datafaker:datafaker:+")

        testCompileOnly("org.projectlombok:lombok:+")
        testAnnotationProcessor("org.projectlombok:lombok:+")
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

