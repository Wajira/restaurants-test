# Your Spring Boot Application

This repository contains the source code and Dockerfile for your Spring Boot application.

## Prerequisites

- [Docker](https://www.docker.com/get-started) installed on your machine.

## Building the Docker Image

To build the Docker image, open a terminal and navigate to the directory containing the Dockerfile.

```bash
docker build -t your-spring-boot-app .

docker run -p 8080:8080 your-spring-boot-app

##Accessing Your Application
Once the Docker container is running, you can access your Spring Boot application by navigating to http://localhost:8080.

## Swagger URL for API Documentation
http://localhost:8080/swagger-ui/
