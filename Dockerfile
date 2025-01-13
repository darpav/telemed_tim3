# Use the official OpenJDK base image from Docker Hub
FROM openjdk:17-jdk-slim AS build

# Set the working directory
WORKDIR /app

# Copy the Gradle wrapper and Gradle files
COPY gradlew gradlew.bat ./
COPY gradle gradle/

# Copy the build.gradle and settings.gradle files
COPY build.gradle settings.gradle ./

# Copy the source code into the container
COPY src src

# Ensure gradlew is executable
RUN chmod +x gradlew

# Run Gradle build to create the .jar file
RUN ./gradlew build --no-daemon

# Stage 2: Running the application
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the .jar file from the build stage
COPY --from=build /app/build/libs/*.jar app.jar

# Expose the port that your Spring Boot app will run on
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
