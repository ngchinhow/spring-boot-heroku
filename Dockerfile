FROM maven:3.9.4-eclipse-temurin-17-alpine

WORKDIR /app

COPY mvnw mvnw.cmd pom.xml ./

COPY src ./src

RUN mvn clean package -DskipTests=true

CMD ["mvn", "spring-boot:run"]