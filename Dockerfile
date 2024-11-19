FROM openjdk:17-jdk-slim as build
WORKDIR /app
COPY target/tuaobra-0.0.1-SNAPSHOT.jar tuaobra.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "tuaobra.jar"]