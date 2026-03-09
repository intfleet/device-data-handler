FROM eclipse-temurin:21-jdk
EXPOSE 8082
COPY target/device-data-handler.jar device-data-handler-app.jar
ENTRYPOINT ["java","-jar","/device-data-handler-app.jar"]