FROM openjdk:17-alpine

MAINTAINER Rojberr rojberr@outlook.com

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]