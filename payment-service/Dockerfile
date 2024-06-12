# Use an official OpenJDK image as a base image
FROM openjdk:11

# Set the working directory inside the container
WORKDIR /app

# Copy the Gradle wrapper and the build.gradle file
COPY gradlew .
COPY build.gradle .

# Copy the Gradle wrapper jar and other files
COPY gradle gradle

# Ensure gradlew has executable permissions
RUN chmod +x gradlew

# Convert line endings to Unix format
RUN apt-get update && apt-get install -y dos2unix && dos2unix gradlew

# Download dependencies
RUN ./gradlew build --no-daemon || true

# Copy the source code
COPY src src

# Build the application
RUN ./gradlew build --no-daemon

# Expose port 8080
EXPOSE 8080

# Command to run your application
CMD ["java", "-jar", "build/libs/your-application.jar"]
