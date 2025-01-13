# Step 1: Use a base image with OpenJDK
FROM openjdk:17-jdk-slim AS build

# Step 2: Set the working directory inside the container
WORKDIR /app

# Step 3: Copy the Gradle build file to the container
COPY gradlew .
COPY gradle ./gradle
COPY build.gradle .
COPY settings.gradle .

# Step 4: Copy the entire project to the container
COPY src ./src

# Step 5: Build the project using Gradle (you can use Maven if applicable)
RUN ./gradlew build --no-daemon

# Step 6: Use a lighter base image for the final image
FROM openjdk:17-jdk-slim

# Step 7: Set the working directory inside the container for the runtime image
WORKDIR /app

# Step 8: Copy the built jar file from the build stage
COPY --from=build /app/build/libs/telemed-0.0.1-SNAPSHOT.jar app.jar

# Step 9: Expose the port your application will run on
EXPOSE 8080

# Step 10: Set the command to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar", "-Dserver.port=8080"]

# (Optional) If you want to use a dynamic port, use the following instead:
# ENTRYPOINT ["java", "-jar", "app.jar", "-Dserver.port=$PORT"]
