FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY target/notification-service-0.0.1-SNAPSHOT.jar notification-service.jar

EXPOSE 8082

CMD ["java", "-jar", "notification-service.jar"]
