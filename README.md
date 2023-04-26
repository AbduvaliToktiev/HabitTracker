# Проект "Трекер привычек"

Дисциплина вырабатывается через привычки. А привычки создаются на основе регулярности действия. 
Поэтому появились различные приложения, которые следят за прогрессом и формированием привычек.

## Запуск

1. Для запуска вам понадобиться **[Java 11](https://www.java.com/ru/)** и выше.
2. СУБД использован **[PostgreSQL 15.0](https://www.postgresql.org/)**.

## Технологии

1. **[Spring Boot](https://spring.io/projects/spring-boot)** проект использует систему сборки на основе **[Maven](https://maven.apache.org/)**.
2. Для работы с базами данных и для сохраниение Java-обьектов используется **[Spring Data Jpa](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)**, для реализации **[Hibernate](https://hibernate.org/)**.
3. **[Spring Security](https://spring.io/projects/spring-security)** для аутентификации и контрола доступа.
4. **[Apache Kafka](https://kafka.apache.org/documentation/)**
5. **[Hibernate Ehcache](https://www.ehcache.org/documentation/2.8/hibernate/)**
6. **[JUnit](https://junit.org/junit5/docs/current/user-guide/)**
7. Для описание **API** и создание документации, в проекте использован **[Swagger](https://swagger.io/).**
````
 http://localhost:8075/swagger-ui.html#
````
8. Для тестирование **API** - **[Postman](https://www.postman.com/)**.

## Настройки и описания проекта

1. Настройки соединение к БД прописаны в файле `src/main/resources/application.proporties` (надо будет создать новую database и прописать username и password
2. Все зависимости и библиотеки прописаны в файле `pom.xml` (при запуска автоматические будут загружены).
3. Сервер порт по умолчанию **'8075'**, при желание можно поменять в файле `src/main/resources/application.proporties`.
4. При запуске проекта, автоматический добавиться нужные таблицы в **PostgreSQL** и необходимы SQL запросы из файла **data.sql** `src/main/resources/data.sql`.
5. Для запуска и настройки Apache kafka: 
- [Установить Apache Kafka version 2.13-3.4.0](https://kafka.apache.org/downloads)
- Настроить Apache kafka. [Видео с установкой и настройкой](https://www.youtube.com/watch?v=EUzH9khPYgs&t=185s)
- Команда для запуска zookeeper:
````
 C:\kafka>.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
````
- Команда для запуска server:
````
 C:\kafka>.\bin\windows\kafka-server-start.bat .\config\server.properties
````
- Команда для создания темы(topic): 
````
 C:\kafka>.\bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic NewTopic --from-beginning
````

## Описание API запросов для контроля доступа 

1. POST запрос "Аутентификация" `http://localhost:8075/api/v1/auth/authentication`

- Пользователь с доступом admin **(ROLE_ADMIN)**: 
````
{
    "username":"mirbek",
    "password":"mirbek"
}
````

2. Post запрос **"Регистрация"** `http://localhost:8075/api/v1/auth/register` в JSON формате, 
- Каждому новому пользователю автоматический присваювается роль **ROLE_USER**, 
- В поле **`email`** (есть обязательная валидация почты), 
- В поле  **`password`** (есть обязательная валидация почты)
````
{
    "username":"ermek",
    "password":"ermek",
    "email":"springboot1212@gmail.com",
    "age":27,
    "gender":"MALE"
}
````

3. Post запрос **"Сброс пароля"**, заполняется почта пользователя в JSON формате.
````
http://localhost:8075/api/v1/auth/reset-password
````
````
{
    "email":"springboot1212@gmail.com"
}
````
на почту придет jwt-token, он необходим при создание нового пароля.

4. Post запрос **"Изменить пароль"**
````
http://localhost:8075/api/v1/auth/change-password
````
````
{
    "email":"springboot1212@gmail.com",
    "token":"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJF0LptZWsiLCJyb2xlcyI6WyJST0xFX1VTRVIiXSwiaWF0IjoxNjgyMTc5Mjk0LCJleHAiOjE2ODIxODI4OTR9.uW5Ktyag87pA7N48aQ5vjugg4H8slTY6sd-AIEX1Dzo",
    "password":"newpassword",
    "passwordConfirmation":"newpassword"
}
````
- Заполняем поле почта, токен, и два поля для пароля и отправляем запрос.

## Описание API запроcов для добавления контента

1. Post запрос **"Save"** `http://localhost:8075/api/v1/profile/save` , для добавления "Профиля" к пользователю.
````
{
    "name":"Taalai",
    "icon":"https://www.flaticon.com/ru/free-icon/people_3722005",
    "language": "KG",
    "colorTheme": "Голубой_закат",
    "user": {
        "id": 1
    }
}
````

2. Post запрос **"Save"** `http://localhost:8075/api/v1/habit/save` , для добавления "Habits" с доступом админ.
- Необходимо авторизоваться с ролем **`ROLE_ADMIN`**, полученный токен добавить в **`Headers`**.
- Key: **`Authorization`**
- Value: **`Bearer_eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJrdWxvbmJla292Iiwicm9sZXMiOlsiUk9MRV9BRE1JTiJdLCJpYXQiOjE2ODAzNTQ2MzAsImV4cCI6MTY4MDM5MDYzMH0.atY0oyZBlf0sizfe717nn1jtb43vApX7yvqAcSX8xMU`** 
- `Bearer_новыйтокен`.
- Postman - Body - raw - JSON
````
{
    "name": "21 day coding in leetcode",
    "description": "В течение 21 дня решать задания на leetcode",
    "target": 1,
    "frequency": "DAILY",
    "startDate": "2023-04-21",
    "endDate": "2023-05-11"
}
````
3. Post запрос **"Save"** `http://localhost:8075/api/v1/progress/save` , для добавления "Progress" Измерение привычки.
````
{
    
    "progressDate": "21.04.2023",
    "target": 1,
    "profile":{
        "id": 2
    },
    "habit":{
        "id": 1
    }
}
````
4. Post запрос **"Save"** `http://localhost:8075/api/v1/achievement/save?profileId=2&habitId=1` , для проверки достижения и сохранение "Achievement".
````
- RequestParam: profileId = 2, habitId = 1
````

## Модульные тесты

- Добавлен **JUnit** тесты для проверки бизнес логики проекта

## Авторы

- **[Kulonbekov Taalai](https://github.com/kulonbekov)**
- **[AbduvaliToktiev](https://github.com/AbduvaliToktiev)**
