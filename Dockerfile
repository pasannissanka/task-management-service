FROM openjdk:21-jdk
MAINTAINER pasannissanka


COPY web/target/task-management-service.jar task-management-service.jar
ENTRYPOINT ["java","-jar","/task-management-service.jar"]
