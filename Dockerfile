FROM eclipse-temurin:17-jdk

# Install Maven manually
RUN apt-get update && apt-get install -y maven

# Set working directory
WORKDIR /app

# Copy your app source code
COPY . .

# Build the project using Maven
RUN mvn clean install

# Run the JAR file
CMD ["java", "-jar", "target/lau-backend-0.0.1-SNAPSHOT.jar"]
