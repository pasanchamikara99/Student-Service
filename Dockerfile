
FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/StudentService-3.0.2.war /app/StudentService-3.0.2.war

EXPOSE 8081

CMD ["java", "-jar", "/app/StudentService-3.0.2.war"]
