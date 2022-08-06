FROM openjdk:8-jdk-alpine
ARG JAR_FILE=build/libs/klm-order-case-0.1.2-0.1.0.jar
WORKDIR /opt/app
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]