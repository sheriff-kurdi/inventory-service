FROM openjdk:17
COPY target/inventory-service-0.0.1-SNAPSHOT.jar inventory-service-0.0.1-SNAPSHOT.jar
EXPOSE 8089
ENTRYPOINT ["java","-jar","/inventory-service-0.0.1-SNAPSHOT.jar"]
