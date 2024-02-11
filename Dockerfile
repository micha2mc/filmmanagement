FROM openjdk:21-slim
EXPOSE 9002
ADD target/filmmanagement-0.0.1.jar film-service.jar
ENTRYPOINT ["java","-jar","/film-service.jar"]