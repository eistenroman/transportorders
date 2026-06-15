FROM eclipse-temurin:17-jre-alpine
ARG JAR_FILE=target/transportorders-0.0.1.jar
COPY ${JAR_FILE} app_transportorders.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app_transportorders.jar"]
