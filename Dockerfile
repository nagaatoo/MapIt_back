FROM openjdk:17-oracle

COPY ./build/libs/mapit-resource-0.0.1-SNAPSHOT.jar ./app/mapit.jar
ENTRYPOINT ["java", "-jar", "./app/mapit.jar"]
EXPOSE 8552