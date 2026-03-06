FROM eclipse-temurin:21-jdk
EXPOSE 8080
COPY target/device-data-handler.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]