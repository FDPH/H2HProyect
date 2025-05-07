FROM maven:3.9.9-amazoncorretto-21-al2023 AS builder
WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline
COPY src ./src

RUN mvn package -DskipTests

# --- Runtime Stage ---
FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

RUN useradd -m myuser && chown -R myuser:myuser /app
USER myuser

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]