FROM openjdk:17-alpine

WORKDIR /app

COPY build/libs/*.jar backend-0.0.1-SNAPSHOT.jar

EXPOSE 8081

CMD ["java", "-jar", "backend-0.0.1-SNAPSHOT.jar"]