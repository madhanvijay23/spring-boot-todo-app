# Use OpenJDK 17 as base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy project files
COPY . .

# Build the app
RUN ./mvnw clean install -DskipTests

# Expose port 8080
EXPOSE 8080

# Run the app
CMD ["java", "-jar", "target/todoapp-0.0.1-SNAPSHOT.jar"]
