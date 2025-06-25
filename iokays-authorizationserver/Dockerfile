# 使用官方 Eclipse Temurin JDK 23 镜像
FROM eclipse-temurin:23-jdk-jammy

# 设置工作目录
WORKDIR /app

# 复制 JAR 文件（Gradle 默认输出路径）
COPY build/libs/*.jar app.jar

# 暴露应用端口（根据你的应用配置调整）
EXPOSE 8888

# 设置非 root 用户（安全最佳实践）
RUN useradd -m appuser && chown -R appuser:appuser /app
USER appuser

# 启动应用
ENTRYPOINT ["java", "-jar", "app.jar"]