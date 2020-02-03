FROM openjdk:8-jdk-alpine
MAINTAINER experto.com
VOLUME /tmp
EXPOSE 8080
ADD build/kocfinans-creditapplication.jar kocfinans-creditapplication.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/kocfinans-creditapplication.jar"]