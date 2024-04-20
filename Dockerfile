FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
COPY src ./src
CMD ["mvn", "spring-boot:run"]
