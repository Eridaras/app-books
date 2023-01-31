FROM eclipse-temurin:17.0.5_8-jre-alpine

RUN mkdir /app
WORKDIR /app

COPY build/libs/app-books.jar app-books.jar
COPY build/libs/libs ./libs

CMD ["java", "-jar", "app-books.jar"]

FROM postgres:12.4

COPY 1-schema.sql /docker-entrypoint-initdb.d/1-schema.sql
COPY 2-data.sql /docker-entrypoint-initdb.d/2-data.sql

ENV POSTGRES_DB=distribuida
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=1234

FROM openjdk:8-jdk-alpine
WORKDIR /app
COPY build/libs/miAplicacion.jar /app/miAplicacion.jar
ENV PORT 8080
EXPOSE 8080
LABEL traefik.http.services.app.loadbalancer.server.port=8080
CMD ["java", "-jar", "miAplicacion.jar"]