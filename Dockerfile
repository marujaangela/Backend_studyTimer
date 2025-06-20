## BUILD Stage ##
FROM gradle:jdk21-jammy AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
ARG DB_PASSWORD
ARG DB_URL
ARG DB_USERNAME
RUN gradle build --no-daemon

## PACKAGE Stage ##
FROM eclipse-temurin:21-jdk-jammy
COPY --from=build /home/gradle/src/build/libs/webtech-project-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
