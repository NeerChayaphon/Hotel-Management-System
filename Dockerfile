FROM openjdk:17

COPY target/springboot-mongodb-0.0.1-SNAPSHOT.jar springboot-mongodb-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "/springboot-mongodb-0.0.1-SNAPSHOT.jar"]