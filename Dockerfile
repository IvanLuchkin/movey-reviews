FROM adoptopenjdk:15-jdk
VOLUME /tmp
ARG JAR_FILE
ADD ${JAR_FILE} review-service-0.0.1.jar
ENTRYPOINT ["java", "-jar", "/review-service-0.0.1.jar"]