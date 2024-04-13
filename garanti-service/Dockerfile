FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY target/garanti-service-0.0.1-SNAPSHOT.jar garanti-service.jar

EXPOSE 8083

CMD ["java", "-jar", "garanti-service.jar"]
