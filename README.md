# CRUDhibernate
 
Консольное приложение, которое использует БД и позволяет выполнять CRUD (CREATE, READ, UPDATE, DELETE) операции для таблиц с использованием hibernate:
- developers
- skills
- companies
- customers
- projects
 
Паттерн MVC.
Все классы должны быть грамотно проименованы и разложены по пакетам.
Рекомендуется активно использовать интерфейсы, абстрактные классы, generics и шаблоны проектирования (Factory method, Builder, etc.)

## Инструкция по разворачиванию
1. Создать схему базы данных с помощью [sql скрипта](https://github.com/kurotkin/CRUDjdbc/blob/master/src/main/resources/init.sql);
2. Заполнить ip адрес базы данных, имя пользователя, пароль в [файле](https://github.com/kurotkin/CRUDjdbc/blob/master/src/main/java/dao/utils/ConnectionUtil.java)
3. Запустить [приложение](https://github.com/kurotkin/CRUDjdbc/blob/master/src/main/java/Program.java) 
