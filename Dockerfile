FROM eclipse-temurin:21-jre-alpine AS runtime
WORKDIR /app
COPY target/StramingSong-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "app.jar"]
