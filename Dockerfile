                             # Use a base image with OpenJDK
                             FROM openjdk:17-jdk-slim

                             # Set the working directory
                             WORKDIR /app

                             # Copy the built jar file from your local machine into the container
                             COPY build/libs/telemed-0.0.1-SNAPSHOT.jar app.jar

                             # Expose the port (usually 8080)
                             EXPOSE 8080

                             # Command to run the application
                             ENTRYPOINT ["java", "-jar", "/app/app.jar", "-Dserver.port=$PORT"]
