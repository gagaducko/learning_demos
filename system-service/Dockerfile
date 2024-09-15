FROM openjdk:17

LABEL authors="gagaduck"

COPY ./target/system-service-0.0.1-SNAPSHOT.jar /app.jar

COPY ./skywalking-agent /usr/local/server/skywalking
#java -javaagent:/usr/local/server/skywalking/skywalking-agent.jar -Dskywalking.agent.service_name=system-service Dskywalking.collector.backend_service=192.168.186.1:11800 -jar /app.jar
#ENTRYPOINT ["java", "-jar", "/app.jar"]
ENTRYPOINT ["java", "-javaagent:/usr/local/server/skywalking/skywalking-agent.jar", "-Dskywalking.agent.service_name=system-service", "-Dskywalking.collector.backend_service=192.168.186.1:11800", "-jar", "/app.jar"]