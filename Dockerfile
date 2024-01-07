FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN chmod 777 ./mvnw
COPY src ./src
RUN ./mvnw install -DskipTests
CMD ["java", "-Dserver.port=$PORT", "-jar", "target/*.jar"]