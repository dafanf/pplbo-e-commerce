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

# Bersihkan dan bangun aplikasi
RUN ./gradlew clean build

# Expose port yang digunakan oleh aplikasi
EXPOSE 8082

# Perintah untuk menjalankan aplikasi saat container dimulai
CMD ["./gradlew", "bootRun"]
