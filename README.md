# Students Api Service

## О проекте

**Проект "Students Api Service"**

**1. Веб-сервис S**

Данная служба (Container 3) связана с двумя хранилищами данных:
а) В базе данных PostgreSQL (Container 4) хранится сущьность студентов.
б) В хранилище MinIO (Container 5) хранятся фотографии студентов из пункта а).
Этот сервис имеет SOAP-интерфейс для взаимодействия с другими системами. 
Используя этот интерфейс можно получить список всех студентов, 
либо только запись об одном. Для этого интерфейса реализованы 
только методы получения данных 
(пример: getAllUnits и getOneUnit/{номер зачётной книжки}).

**2. Веб-сервис R**

Данный сервис (Container 1) имеет два интерфейса:
а) REST API для взаимодействия с пользователями;
б) SOAP для взаимодействия с другими системами.
Внутри этого сервиса реализованы алгоритмы трансформации данных 
из JSON в XML и обратно.


**3. Брокер**

В качестве брокера (Container 2) используется  Apache Kafka.
Задачей брокера является передача данных на Веб-сервис S, который работает на REST API;

### Для запуска необходимо:

1. [x] Java 21
2. [x] Maven 3.8.6
3. [x] PostgreSql 16

### Стек используемых технологий:
* Java 21
* Maven 3.8.6
* Spring-boot 3.3.0
* * Web
* * Web-services
* * Data Jpa
* * Security
* PostgreSQL 42.5.4
* Liquibase 4.22.0
* Lombok 1.18.26
* JAX-WS
* Apache Kafka
* Checkstyle
* Swagger
* Docker

### Запуск приложения:

+ Создать базу данных командой ```create database cd_students```;
+ Запустить Zookeeper (docker-compose up zookeeper)
+ Запустить Kafka (docker-compose up brocker)
+ Запустить minIO (docker-compose up minio)
+ Запустить сервис Сервис S ```mvn spring-boot:run```;
+ Запустить сервис Сервис R```mvn spring-boot:run```;

_Контакты для связи:_

__**email: kbus94@yandex.ru**__

__**telegram: @NicolBu**__

