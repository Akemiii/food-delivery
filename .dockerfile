FROM openjdk:11-jre-slim

WORKDIR /app

COPY target/food-delivery.jar /app/food-delivery.jar

CMD ["java", "-jar", "food-delivery.jar"]
