FROM amazoncorretto:17-alpine-jdk
MAINTAINER pablo.developerweb@gmail.com
COPY target/mini-autorizador-0.0.1.jar mini-autorizador-0.0.1.jar
ENTRYPOINT ["java","-jar","/mini-autorizador-0.0.1.jar"]