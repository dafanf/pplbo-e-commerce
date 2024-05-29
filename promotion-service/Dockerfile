# Gunakan base image yang sudah memiliki Java runtime
FROM openjdk:17-jdk-slim

# Set direktori kerja di dalam container
WORKDIR /app

# Salin Gradle wrapper dan file build
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

# Salin kode sumber
COPY src src

# Berikan izin eksekusi pada skrip gradlew
RUN chmod +x ./gradlew

# Bangun aplikasi
RUN ./gradlew build

# Salin file JAR yang dihasilkan oleh proyek Gradle ke dalam container
COPY build/libs/*.jar app.jar

# Expose port yang digunakan oleh aplikasi
EXPOSE 8082

# Perintah untuk menjalankan aplikasi saat container dimulai
CMD ["java", "-jar", "app.jar"]