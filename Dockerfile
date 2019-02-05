ARG JDK_IMAGE_VERSION=latest
FROM openjdk:${JDK_IMAGE_VERSION}
MAINTAINER Sungbin Cho <sbeinone@gmail.com>

ENV SOURCE_ARTIFACT build/libs/yellowcard-api-0.0.1.jar
ENV DESTINATION_ARTIFACT /opt/yellowcard/yellowcard-api-0.0.1.jar

ADD ${SOURCE_ARTIFACT} ${DESTINATION_ARTIFACT}

ENTRYPOINT ["java", "-jar"]
CMD ["-Dspring.profiles.active=production", "/opt/yellowcard/yellowcard-api-0.0.1.jar"]