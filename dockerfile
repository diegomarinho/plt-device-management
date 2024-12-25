# Using the Maven base image with OpenJDK 17 based on Eclipse Temurin
FROM maven:3.8.6-eclipse-temurin-17 AS build

# Setting the working directory
WORKDIR /app

# Copying the pom.xml file and downloading dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copying the application's source code
COPY src ./src

# Building the package
RUN mvn clean package

# Stage 2: Configuration to run the application with Azul Zulu JRE
FROM azul/zulu-openjdk-alpine:17-jre AS runtime

# Setting the working directory
WORKDIR /app

# Copying the JAR to the final image
COPY --from=build /app/target/plt-device-management.jar /app/

# Exposing port 8085 (or the port your application uses)
EXPOSE 8085

# Command to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "plt-device-management.jar"]
