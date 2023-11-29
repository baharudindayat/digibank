#required
FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/digibank-0.0.1-SNAPSHOT.jar .

EXPOSE 8080

ENTRYPOINT ["java","-jar","digibank-0.0.1-SNAPSHOT.jar"]