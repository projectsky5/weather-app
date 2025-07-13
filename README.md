ДЗ №1 T1 Иннотех

---

Микросервисное приложение на Spring Boot + Kafka, состоящее из двух сервисов:

	•	weather-producer - отправляет события о погоде в Kafka.
	•	weather-consumer - читает события из Kafka и собирает аналитику.

Общий DTO хранится в модуле weather-common.

---

**Инструкция запуска**

  Зависимости:
  
    •	Установлен Docker + Docker Compose
    •	JDK 21
    •	Apache Maven

**1. Поднять Kafka**

В корне проекта выполнить команду
  ```
  docker-compose up -d
  ```

**2. Собрать проект**

  ```
  mvn clean install
  ```

**3. Запустить weather-producer**

  ```
  cd weather-producer
  mvn spring-boot:run
  ```
  Доступен на http://localhost:8080

**4. Запустить weather-consumer**

  ```
  cd weather-consumer
  mvn spring-boot:run
  ```
  Доступен на http://localhost:8081

---

**Реализованный функционал**

  weather-producer:
  
	•	Отправка сообщений о погоде в Kafka (weather-topic).
	•	Генерация случайной погоды по городам.
	•	REST эндпоинт для добавления новых городов.

  weather-consumer:
  
	•	Получение сообщений из Kafka (weather-topic).
	•	Ведение статистики по городам и погодным условиям (Солнечно, Дождь, Облачно).
	•	REST эндпоинт для получения аналитики.

  weather-common:
  
	•	DTO: WeatherEvent.

 


