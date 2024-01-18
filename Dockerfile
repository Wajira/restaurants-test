# Use the official OpenJDK 8 base image
FROM openjdk:8-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file into the container at /app
COPY target/restaurant-0.0.1.jar /app/restaurant-0.0.1.jar

# Expose the port that your application will run on
EXPOSE 8080

# Specify the command to run on container start
CMD ["java", "-jar", "/app/restaurant-0.0.1.jar"]
