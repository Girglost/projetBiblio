FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY ./target/*.jar biblio-boot.jar

CMD [ "java", "-jar", "/app/biblio-boot.jar" ]

EXPOSE 8080
