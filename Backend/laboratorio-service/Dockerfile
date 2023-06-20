FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} laboratorio-service.jar
ENTRYPOINT ["java","-jar","/laboratorio-service.jar"]