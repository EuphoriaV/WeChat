# WeChat

## Запуск

Чтобы локально запустить проект у себя, выполните следующие команды:

```
git clone https://github.com/EuphoriaV/WeChat.git
```

Затем в директории проекта выполните:

```
docker compose up -d
```

Таким образом запустится база данных, накатятся миграции и запустится сам сервис.

## Стек технологий

Проект написан на Java 17 с использованием Spring Boot 3.2.3 и следующих спринговых модулей:
- Spring Web MVC
- Spring Security
- Spring Validation
- Spring Data JPA

В качестве базы данных используется PostgreSQL, миграции накатываются с помощью Liquibase.


Также в проекте есть юнит и интеграционные тесты, в которых используются следующие технологии:
- Spring Boot Test
- JUnit 5
- Mockito
- Testcontainers

На фронтенде используются следующие языки/технологии:
- HTML
- CSS
- Freemarker
- jQuery

Проект собирается с помощью Maven. Также с помощью Docker собирается образ сервиса, и вместе с базой данных и миграциями Liquibase разворачивается с помощью Docker Compose.

Также в проекте настроены два workflow в Github Actions: сборка проекта и выполнение миграций Liquibase.

## Примеры использования
![image](https://github.com/EuphoriaV/WeChat/assets/78645533/3887601f-6717-4fa9-8dc4-9d7e02b5968c)
![image](https://github.com/EuphoriaV/WeChat/assets/78645533/6f26999d-bec2-4341-8cf3-40ed279cd63a)
![image](https://github.com/EuphoriaV/WeChat/assets/78645533/2efce5b5-ddf0-40d5-bc91-aed855b8fec0)
![image](https://github.com/EuphoriaV/WeChat/assets/78645533/d8cd8308-c441-4470-8130-f8f18648ef2e)
![image](https://github.com/EuphoriaV/WeChat/assets/78645533/e09d5b20-3c74-4bf3-a6a3-8d47f8f2a095)
![image](https://github.com/EuphoriaV/WeChat/assets/78645533/87d802fc-14f9-43a5-a0da-128043f7b6f1)
