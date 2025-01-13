# # Importing JDK and copying required files
# FROM openjdk:19-jdk AS build
# WORKDIR /app
# COPY pom.xml .
# COPY src src
#
# # Copy Maven wrapper
# COPY mvnw .
# COPY .mvn .mvn
#
# # Set execution permission for the Maven wrapper
# RUN chmod +x ./mvnw
# RUN ./mvnw clean package -DskipTests
#
# # Stage 2: Create the final Docker image using OpenJDK 19
# FROM openjdk:19-jdk
# VOLUME /tmp
#
# # Copy the JAR from the build stage
# COPY --from=build /app/target/*.jar app.jar
# ENTRYPOINT ["java","-jar","/app.jar"]
# EXPOSE 8080
# Stage 1: Build the application with Gradle
FROM gradle:7.5-jdk19 AS build
WORKDIR /app

# Copy build files
COPY build.gradle .
COPY settings.gradle .
COPY src src

# Build the application
RUN gradle build -x test

# Stage 2: Create the final Docker image using OpenJDK 19
FROM openjdk:19-jdk
VOLUME /tmp

# Copy the JAR from the build stage
COPY --from=build /app/build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080
