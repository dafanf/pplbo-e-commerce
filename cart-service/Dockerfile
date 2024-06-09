# Use the official OpenJDK 17 image as a base image
FROM amazoncorretto:17.0.11-al2023-headless

# Set the working directory in the container
WORKDIR /app

# Copy the build output to the container
COPY . .

RUN ./gradlew build

# Expose the port that the application will run on
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "app/build/libs/app.jar"]
