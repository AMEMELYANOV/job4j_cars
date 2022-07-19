[![Build Status](https://app.travis-ci.com/AMEMELYANOV/job4j_cars.svg?branch=master)](https://app.travis-ci.com/AMEMELYANOV/job4j_cars)
[![codecov](https://codecov.io/gh/AMEMELYANOV/job4j_cars/branch/master/graph/badge.svg?token=wDmLHaxxgl)](https://codecov.io/gh/AMEMELYANOV/job4j_cars)

# job4j_cars

## Описание проекта
Web приложение, площадка для размещения объявлений по продаже автомобилей.
# Функционал:
* Регистрация пользователей, доступно редактирование профиля.
* Аутентификация и авторизация пользователей через Servlet Filter.
* Добавление, редактирование, удаление объявлений.
* Просмотр списка своих объявлений.
* Изменения статуса объявлений, перевод в категорию проданных.
# Применяемые технологии:
* Java 14
* Spring Boot 2,  Java EE Servlets
* HTML, Bootstrap CSS, Thymeleaf
* Hibernate, PostgresSQL
* JUnit, Mockito
# Применяемые инструменты:
* Maven, Checkstyle

## Сборка и запуск:
Для выполнения действий данного раздела необходимо установить 
и настроить систему сборки проектов Maven.

По умолчании проект компилируется и собирается в директорию target.  
### 1. Компиляция и запуск проекта.
Команда для компиляции
`mvn compile`

Команда для запуска проекта
`mvn exec:java -Dexec.mainClass="ru.job4j.cars.Application"`
### 2. Сборка и запуск проекта.
Команда для сборки в war
`mvn clean package -DskipTests`

Команда для запуска
`mvn spring-boot:run`

## Примеры работы:

### 0. Страница приветствия.
Со страницы приветствия пользователь может перейти к регистрации, либо к странице входа.

![alt text](images/cars_img_1.jpg)

### 1. Регистрация пользователя.
Каждому пользователю выдается пара наименование аккаунта (email) и пароль.
Помимо имени аккаунта и пароля указываются: имя пользователя и телефон, эта контактная
информация отображаются на странице объявления.

![alt text](images/cars_img_2.jpg)

### 2. Страница входа.
Для входа необходимо ввести пароль и электронную почту (наименование аккаунта), указанную при регистрации.

![alt text](images/cars_img_3.jpg)

### 3. Список всех объявлений.
На странице выводится список всех объявлений пользователей сайта.

![alt text](images/cars_img_4.jpg)

### 4. Страница объявления с подробностями.
На странице выводится объявление со всей доступной информацией: комментарии продавца,
контактная информация (имя, телефон).

![alt text](images/cars_img_8.jpg)

### 5. Страница "моих" объявлений.
На странице выводится объявления созданных пользователем приложения.
Присутствуют кнопки редактирования и удаления объявлений.

![alt text](images/cars_img_5.jpg)

### 6. Страница создания объявления.
На странице заполняется информация по продаваемому автомобилю, при 
необходимости загружается фотография.

![alt text](images/cars_img_6.jpg)

### 7. Страница редактирования объявления.
На странице редактируется информация по поданному ранее объявлению.

![alt text](images/cars_img_7.jpg)

## TODO list
* Добавить JavaDoc
* Добавить поддержку миграции
* Добавить в другую ветку работу с пользователями через Spring Security

## Контакт

[![alt-text](https://img.shields.io/badge/-telegram-grey?style=flat&logo=telegram&logoColor=white)](https://t.me/T_AlexME)&nbsp;&nbsp;
[![alt-text](https://img.shields.io/badge/@%20email-005FED?style=flat&logo=mail&logoColor=white)](mailto:amemelyanov@yandex.ru)&nbsp;&nbsp;