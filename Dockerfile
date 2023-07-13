#образ взятый за основу
FROM openjdk:17
#Записываем в переменную путь до варника (необязательно)
ARG jarFile=target/M20Service-0.0.1-SNAPSHOT.war
#Куда переместить варник внутри контейнера
WORKDIR /opt/app
#копируем наш джарник в новый внутри контейнера
COPY ${jarFile} m20service.war
#Открываем порт
EXPOSE 9090
#Команда для запуска
ENTRYPOINT ["java", "-jar", "m20service.war"]
