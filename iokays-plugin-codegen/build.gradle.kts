apply(plugin = "org.springframework.boot")
apply(plugin = "io.spring.dependency-management")
apply(plugin = "org.jetbrains.kotlin.jvm") //kotlin
apply(plugin = "org.jetbrains.kotlin.plugin.spring") //kotlin-spring

dependencies {
    implementation(project(":common-core"))

    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")

    implementation("org.postgresql:postgresql:42.7.5")

    // JPA的QueryDSL的查询类生成 [因为用到了其他模块使用的基类和接口，那么其他模块也要生成]
    // 生成依赖Entity实体
//    implementation("com.querydsl:querydsl-jpa:5.1.0")
//    annotationProcessor("com.querydsl:querydsl-apt:5.1.0:jakarta")
//    annotationProcessor("jakarta.persistence:jakarta.persistence-api:3.2.0")

    // JDBC的QueryDSL的查询类生成 [该部分的业务逻辑是抛开领域模型，面向表结构的查询，主要用于后端的简单报表，相对复杂的列表，详情的查询]
    // 生成没有任何依赖，建议放在单独的模块中.
    implementation("com.querydsl:querydsl-sql-codegen:5.1.0")

    compileOnly("org.projectlombok:lombok:1.18.34")
    annotationProcessor("org.projectlombok:lombok:1.18.34")
}

tasks.register<JavaExec>("generateQuerydsl") {
    group = "build"
    description = "Generate QueryDSL Q classes for SQL"

    // 设置类路径
    classpath = sourceSets["main"].runtimeClasspath + sourceSets["main"].output + configurations["annotationProcessor"]

    println("classpath: " + classpath)


    // 指定主类（Querydsl SQL 代码生成的入口）
    mainClass.set("com.iokays.plugin.querydsl.QueryDSLExporter")

    // 配置代码生成参数
    args = listOf(
        "-source", "src/main/java",             // 输入源目录
        "-target", "src/main",  // 输出目录
        "-package", "com.iokays.core.adapter.web.table",    // 生成的 Q 类的包名
        "-url", "jdbc:h2:D:/Data/h2/db-iokays-authoriazationserver;AUTO_SERVER=TRUE;MODE=MySQL" // 数据库连接 URL
    )
}

