# Этап 1 - сборка проекта в jar
FROM maven:3.8.6-openjdk-21 as maven
RUN mkdir serv_s
WORKDIR serv_s
COPY . .
RUN mvn package -Dmaven.test.skip=true
CMD ["mvn", "liquibase:update", "-Pdocker"]

# Этап 2 - указание как запустить проект
FROM openjdk:21.0.4-jdk
WORKDIR serv_s
COPY --from=maven /serv_s/target/serv_s-0.0.1-SNAPSHOT.jar app.jar
CMD java -jar app.jar