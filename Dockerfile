FROM openjdk:17.0.2-oracle

COPY build/libs/kotlin-restful-api-0.0.1-SNAPSHOT.jar /app/application.jar

CMD ["java", "-jar", "app/application.jar"]