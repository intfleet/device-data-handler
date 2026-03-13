FROM eclipse-temurin:21-jdk
# Create log directory
RUN mkdir -p /opt/intellifleet/apps/logs/device-data-handler
EXPOSE 8082
COPY target/device-data-handler.jar device-data-handler-app.jar
ENTRYPOINT ["java","-jar","/device-data-handler-app.jar"]