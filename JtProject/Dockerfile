# Stage 1: Build the application using Maven to create a .war file
FROM maven:3.8-openjdk-11 AS builder

WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Create the final image using an official Tomcat server
FROM tomcat:9-jdk11

# Remove the default Tomcat welcome page
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy the .war file from the builder stage into Tomcat's deploy directory
# Rename it to ROOT.war so it deploys to the root URL
COPY --from=builder /app/target/*.war /usr/local/tomcat/webapps/ROOT.war

# Expose the port Tomcat runs on
EXPOSE 8080
