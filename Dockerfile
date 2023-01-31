FROM eclipse-temurin:17.0.5_8-jre-alpine

RUN mkdir /app
WORKDIR /app

COPY build/libs/app-books.jar app-books.jar
COPY build/libs/libs ./libs

CMD ["java", "-jar", "app-books.jar"]
