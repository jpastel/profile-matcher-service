FROM openjdk:17
EXPOSE 8080
COPY target/profile-matcher-service-*.jar /profile-matcher-service.jar
ENTRYPOINT ["java","-jar","/profile-matcher-service.jar"]