FROM openjdk:17-jdk-alpine
WORKDIR /app
# Copiar solo el JAR generado por Maven
COPY target/AutoBackEnd3-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 3003
CMD ["java", "-jar", "app.jar"]