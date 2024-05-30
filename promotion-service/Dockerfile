# Gunakan base image yang sudah memiliki Java runtime
FROM openjdk:17-slim

# Set direktori kerja di dalam container
WORKDIR /app

# Salin JAR file
COPY build/libs/*.jar app.jar

CMD ["java", "-jar", "app.jar"]