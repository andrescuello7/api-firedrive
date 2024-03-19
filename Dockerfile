# Usamos una imagen base de OpenJDK
FROM openjdk:11-jre-slim

# Establecemos el directorio de trabajo en /app
# WORKDIR /app

# Exponemos el puerto en el que se ejecutará la aplicación Spring Boot
EXPOSE 8080

# Copiamos el archivo JAR de tu aplicación al contenedor
ADD target/spring-boot-docker.jar spring-boot-docker.jar

# Comando para ejecutar la aplicación Spring Boot
ENTRYPOINT ["java", "-jar", "/spring-boot-docker.jar"]
