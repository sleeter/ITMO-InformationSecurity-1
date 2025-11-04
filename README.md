# ITMO Information Security Lab Project

## Описание проекта
Проект реализует REST API на Java Spring Boot с базовыми функциями авторизации и работы с постами.

### Доступные эндпоинты

| Метод | Эндпоинт       | Описание                  | Параметры           |
|-------|----------------|---------------------------|--------------------|
| POST  | `/auth/login`  | Авторизация пользователя  | `name`, `password` |
| POST  | `/api/post`    | Создание нового поста     | `description`      |
| GET   | `/api/post`    | Получение всех постов     | —                  |

Пример запроса для авторизации:

```bash
curl -X POST http://localhost:8080/auth/login \
-H "Content-Type: application/json" \
-d '{"name":"user1","password":"password1"}'
```

Пример запроса для создания поста:
```bash
curl -X POST http://localhost:8080/api/post \
-H "Authorization: Bearer <JWT_TOKEN>" \
-H "Content-Type: application/json" \
-d '{"description":"Hello World"}'
```

Пример запроса для получения всех постов:
```bash
curl -X GET http://localhost:8080/api/post \
-H "Authorization: Bearer <JWT_TOKEN>"
```

## Реализованные меры безопасности

1. **Защита от SQL-инъекций**
    - Используется Spring Data JPA с PreparedStatement, что исключает возможность внедрения вредоносного SQL-кода.

2. **Защита от XSS (Cross-Site Scripting)**
    - Все пользовательские данные экранируются перед выводом.

3. **Аутентификация и авторизация**
    - Реализована через Spring Security и кастомный middleware `JwtRequestFilter`.
    - Все защищенные эндпоинты требуют валидный JWT-токен.
    - Только авторизованные пользователи могут создавать или просматривать посты.


## Скриншоты отчетов SAST / SCA

### SAST (SpotBugs / Static Analysis)
![SAST Report](static/spotbugs-report.png)

## Последний успешный запуск CI/CD

[Последний успешный запуск pipeline](https://github.com/sleeter/ITMO-InformationSecurity-1/actions/runs/19081168531/job/54509874464)

