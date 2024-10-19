FROM eclipse-temurin:21
COPY target/truth-keeper*.jar truth-keeper.jar
EXPOSE 9001
ENTRYPOINT ["java", "-jar", "truth-keeper.jar"]