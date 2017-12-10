# CRUDhibernate
 
Консольное приложение, которое использует БД и позволяет выполнять CRUD (CREATE, READ, UPDATE, DELETE) операции для таблиц с использованием hibernate:
- developers
- skills
- companies
- customers
- projects

## Инструкция по разворачиванию
1. Создать базу данных HibernateCRUD - `CREATE DATABASE IF NOT EXISTS HibernateCRUD;` или с помощью [sql скрипта](https://github.com/kurotkin/CRUDhibernate/blob/master/src/main/resources/init.sql);
2. Заполнить ip адрес базы данных, имя пользователя, пароль в [файле](https://github.com/kurotkin/CRUDhibernate/blob/master/src/main/resources/hibernate.cfg.xml) hibernate.cfg.xml
3. Запустить [приложение](https://github.com/kurotkin/CRUDhibernate/blob/master/src/main/java/Program.java) 
