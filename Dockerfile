FROM openjdk:17-jdk-slim AS build
WORKDIR /app
COPY . .
RUN ./mvnw package

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar ./wechat.jar
CMD ["java", "-jar", "wechat.jar"]
