FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY target/akbank-service-0.0.1-SNAPSHOT.jar akbank-service.jar

EXPOSE 8081

CMD ["java", "-jar", "akbank-service.jar"]
