apply(plugin = "org.springframework.boot")
apply(plugin = "io.spring.dependency-management")

dependencies {

    implementation(project(":common-core"))
    implementation("commons-validator:commons-validator:1.9.0")

    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    implementation("org.postgresql:r2dbc-postgresql:1.0.7.RELEASE")

    implementation("org.springframework.boot:spring-boot-starter-webflux")

    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")

    // JTS (JTS Topology Suite) 核心库，用于处理地理空间数据
    implementation("org.locationtech.jts:jts-core:1.20.0")
    // JSQLParser 是一个 SQL 解析器，用于解析和操作 SQL 语句
    implementation("com.github.jsqlparser:jsqlparser:5.2")
    // JSQL格式化工具，用于美化SQL语句
    implementation("com.manticore-projects.jsqlformatter:jsqlformatter:5.0")

    compileOnly("org.projectlombok:lombok:1.18.34")
    annotationProcessor("org.projectlombok:lombok:1.18.34")

    implementation("org.mapstruct:mapstruct:1.6.3")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.6.3")


    testImplementation("org.springframework.boot:spring-boot-starter-test")

}
