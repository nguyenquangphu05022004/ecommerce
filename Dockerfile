# Build stage
FROM maven:3.8.7-openjdk-18 AS build
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# Runtime stage
FROM amazoncorretto:17
ARG PROFILE=dev
ARG APP_VERSION=1.0.0

WORKDIR /app
COPY --from=build /build/target/ecommerce-*.jar /app/

ENV DB_URL=jdbc:mysql://mysql_db:3306/ecommerce_v5?createDatabaseIfNotExist=true
ENV DB_USERNAME=root
ENV DB_PASSWORD=root

ENV REDIS_PORT=6379
ENV REDIS_HOST=redis_db

EXPOSE 8081
ENV ACTIVE_PROFILE=${PROFILE}
ENV JAR_VERSION=${APP_VERSION}

CMD java -jar -Dspring.datasource.url=${DB_URL} -Dspring.datasource.username=${DB_USERNAME} -Dspring.datasource.password=${DB_PASSWORD} -Dspring.data.redis.host=${REDIS_HOST} -Dspring.data.redis.port=${REDIS_PORT} ecommerce-${JAR_VERSION}.jar