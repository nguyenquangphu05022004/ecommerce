FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY .mvn/ .mvn

RUN chmod +x mvnw
COPY mvnw pom.xml ./
COPY src ./src
CMD ["./mvnw", "spring-boot:run"]
