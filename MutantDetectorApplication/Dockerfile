
FROM eclipse-temurin:17-jdk-jammy as build
WORKDIR /workspace
COPY . .
RUN ./gradlew bootJar --no-daemon
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY --from=build /workspace/build/libs/MutantDetectorApplication-*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]
