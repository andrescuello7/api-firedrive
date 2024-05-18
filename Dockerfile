FROM maven:3.8.3-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/spring-boot-docker.jar spring-boot-docker.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "spring-boot-docker.jar"]