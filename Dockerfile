FROM openjdk:17-jdk-alpine
LABEL authors="Tatiana Pastel"
ARG JAR_FILE=target/*.jar
COPY ./target/task-five-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]