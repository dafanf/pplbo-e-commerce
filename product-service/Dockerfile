# Use a minimal OpenJDK image as base
FROM openjdk:17-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file into the container
COPY build/libs/*.jar app.jar

# Define the command to run your application
CMD ["java", "-jar", "app.jar"]
