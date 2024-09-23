FROM openjdk:17

LABEL authors="gagaduck"

COPY ./target/distributed-redis-example-0.0.1-SNAPSHOT.jar /app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]