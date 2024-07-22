FROM openjdk:21-jdk-slim
LABEL authors="moham"

WORKDIR /app
COPY target/insurance-policy-0.0.1-SNAPSHOT.jar /app/insurance-policy.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "insurance-policy.jar"]
