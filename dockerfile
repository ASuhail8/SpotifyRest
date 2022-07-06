FROM openjdk:8u191-jre-alpine3.9
WORKDIR /spotifyApi
USER root
RUN apk update && apk add maven
ADD src src
ADD pom.xml pom.xml
ADD Logs Logs

ENTRYPOINT mvn clean test -DbaseUrl=test