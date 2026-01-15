# Build stage
FROM eclipse-temurin:21-jdk AS build

# Copy project files
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src ./src

# Make mvnw executable
RUN chmod +x mvnw

# Download dependencies
RUN ./mvnw -B -q dependency:go-offline

# Package the app
RUN ./mvnw -B -q package -DskipTests

# Runtime stage (slim JRE)
FROM eclipse-temurin:21-jre
WORKDIR /app

COPY --from=build target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]