# Use the official OpenJDK base image
FROM openjdk:19-alpine

# Metadata as described above
LABEL maintainer="constantin.nimigean@gmail.com"
LABEL version="1.0"
LABEL description="Docker image for formare-service Spring Boot application"

# Set the current working directory inside the image
WORKDIR /app
EXPOSE 8080
# Copy maven executable to the image
COPY mvnw .
COPY .mvn .mvn


# Copy the pom.xml file to download dependencies
COPY pom.xml .

# Build all the dependencies in preparation to go offline.
# This is a separate step so the dependencies will be cached unless changes to pom.xml are made.
RUN mvn dependency:go-offline -B

# Copy the project source
COPY src src

# Package the application
RUN mvn package -DskipTests

# Specify the start command and entry point of the Spring Boot application
ENTRYPOINT ["java","-jar","/app/target/formare-0.0.1-SNAPSHOT.jar"]