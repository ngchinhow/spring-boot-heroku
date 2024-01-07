FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN chmod 777 ./mvnw
COPY src ./src
RUN ./mvnw install -DskipTests

# Prepare database connection details
ARG jdbc_database_url
ARG jdbc_database_username
ARG jdbc_database_password
ENV JDBC_DATABASE_URL $jdbc_database_url
ENV JDBC_DATABASE_USERNAME $jdbc_database_username
ENV JDBC_DATABASE_PASSWORD $jdbc_database_password
RUN echo $JDBC_DATABASE_URL
RUN echo $JDBC_DATABASE_USERNAME
RUN echo $JDBC_DATABASE_PASSWORD
CMD ["./mvnw", "spring-boot:run"]