FROM maven:3.9.6-eclipse-temurin-21 as build

WORKDIR /app

COPY pom.xml .
COPY src src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre

COPY --from=build /app/target/wishlist-api.jar /app/wishlist-api.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "/app/wishlist-api.jar"]