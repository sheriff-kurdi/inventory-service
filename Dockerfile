FROM openjdk:17-jdk-slim-buster
COPY target/inventory-service-0.0.1-SNAPSHOT.jar inventory-service-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/inventory-service-0.0.1-SNAPSHOT.jar"]
