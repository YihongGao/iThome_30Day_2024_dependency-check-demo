FROM eclipse-temurin:17.0.8.1_1-jre

WORKDIR /app

# 創建一個非 root 用戶
RUN groupadd -r appgroup && useradd -r -g appgroup appuser

# 切換到用戶 appuser
USER appuser

ADD target/dependency/opentelemetry-javaagent.jar /app/opentelemetry-javaagent.jar
COPY target/*.jar /app/application.jar

# 定義容器啟動命令，運行 JAR 文件
CMD ["java", "-jar", "-javaagent:opentelemetry-javaagent.jar", "application.jar"]

# 暴露 8080 端口
EXPOSE 8080