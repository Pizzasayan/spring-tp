FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
LABEL version="1.0.0"
LABEL maintainer="fardasafari@gmail.com"
EXPOSE 9000
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} sl-api.jar 
ENTRYPOINT ["java","-jar","/sl-api.jar"]
