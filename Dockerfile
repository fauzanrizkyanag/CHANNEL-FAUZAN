# Stage 1: Build aplikasi menggunakan Maven dan JDK 21
FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# Stage 2: Menjalankan aplikasi menggunakan JRE 21
FROM eclipse-temurin:21-jre

RUN addgroup --system app && adduser --system --group app

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

RUN chown -R app:app /app

USER app

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]