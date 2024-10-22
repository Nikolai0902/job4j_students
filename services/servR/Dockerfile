# Этап 1 - сборка проекта в jar
FROM maven:3.8.6-openjdk-21 as maven
RUN mkdir serv_r
WORKDIR serv_r
COPY . .
RUN mvn package -Dmaven.test.skip=true
CMD ["mvn", "liquibase:update", "-Pdocker"]

# Этап 2 - указание как запустить проект
FROM openjdk:21.0.4-jdk
WORKDIR serv_r
COPY --from=maven /serv_r/target/serv_r-0.0.1-SNAPSHOT.jar app.jar
CMD java -jar app.jar


