# Простой проект для освоения навыков работы с MariaDb

## Dependencies

Использовал [MariaDB Java Client]([https://mvnrepository.com/artifact/org.xerial/sqlite-jdbc/3.44.1.0](https://mvnrepository.com/artifact/org.mariadb.jdbc/mariadb-java-client/3.3.2))

## Что может делать приложение?
- создавать базу данных
- создавать таблицу
- добавлять записи в таблицу
- выводить записи согласно заданию

## Структура БД
![image](https://github.com/bmsalikhov/MariaDB_training/assets/153372291/3920038a-264e-4ea6-8c11-ec8a978e82a7)


## Packages
### data_source
Здесь хранится класс DbManager, в котором реализованы основные методы работы с БД.
#### Методы
- void createTable(String tableName) - создает таблицу
- void insertEmployees(List<Employee> employeeList, String tableName) - заносит записи с "работниками" в ранее созданную таблицу
- void selectWithLetters(String tableName) - выводит записи из таблицы, где в имени есть буквы b или c
- void selectWithYear(String tableName) - выводит работников, которые устроились в 1987 году
- void selectMaxAndMinSalary(String tableName) - выводит максимальную и минимальную зарплату
- void selectEven(String tableName) - выводит четные строки из таблицы
- void selectOdd(String tableName) - выводит нечетные строки из таблицы
### models
Здесь хранится класс Employee, созданный для создания сущности "работник" для последующей записи её в базу данных.
### main
Здесь хранится класс Main, в котором мы создаем список "работников" и далее тестируем описанные выше методы.
