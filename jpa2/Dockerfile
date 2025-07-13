# Используем официальный образ с Java
FROM openjdk:21-jdk-slim

# Рабочая папка в контейнере
WORKDIR /app

# Копируем JAR-файл внутрь контейнера
COPY target/jpa2-0.0.1-SNAPSHOT.jar app.jar

# Указываем команду запуска
ENTRYPOINT ["java", "-jar", "app.jar"]
