FROM openjdk:17-jdk-slim AS build
WORKDIR /app
COPY . .
RUN chmod +x ./mvnw
RUN ./mvnw -Dmaven.test.skip=true package

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/wechat.jar ./
CMD ["java", "-jar", "wechat.jar"]
