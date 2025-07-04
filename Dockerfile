# Use an OpenJDK image
FROM openjdk:21-jdk-slim

# Set app directory inside container
WORKDIR /app

# Copy the built jar into the container
COPY target/emissions-monitoring-api-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080
EXPOSE 8080

# Start the app
ENTRYPOINT ["java", "-jar", "app.jar"]
