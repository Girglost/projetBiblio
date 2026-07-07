FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY ./target/*.jar biblio-quarkus.jar

EXPOSE 8080

CMD [ "java", "-jar", "biblio-quarkus.jar" ]
