FROM eclipse-temurin:21-jdk AS build

WORKDIR /app

COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn

RUN ./mvnw -B -q dependency:go-offline

COPY src src

RUN ./mvnw -B -q clean package -DskipTests

FROM eclipse-temurin:21-jre

WORKDIR /app

RUN useradd -r -u 1001 spring
USER spring

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/app.jar"]
