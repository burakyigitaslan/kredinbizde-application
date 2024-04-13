FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY target/kredinbizde-discovery-0.0.1-SNAPSHOT.jar kredinbizde-discovery.jar

EXPOSE 8761

CMD ["java", "-jar", "kredinbizde-discovery.jar"]
