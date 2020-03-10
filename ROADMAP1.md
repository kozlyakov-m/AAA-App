# План выполнения первого набора требований

#### 0. Подготовительные шаги
1. Включить toggl
2. Прочитать требования
3. Создать репозиторий
4. Создать пустой проект в Idea

#### 1. Описать тестовые данные
1. Описать пользователей  
 
 № | login | pass 
 --- | --- | ---  
 U1 | vasya | 123  
 U2 | admin | admin  
 U3 | q | ?!#  
 U4 | abcdefghij | pass

2. Описать ресурсы  
 
 № | res | role | user
 --- | --- | --- | ---
 R1 | A | READ | vasya
 R2 | A.B.C | WRITE | vasya
 R3 | A.B | EXECUTE | admin
 R4 | A | READ | admin
 R5 | A.B | WRITE | admin
 R6 | A.B.C | READ | admin
 R7 | B | EXECUTE | q
 
#### 2. Записать тесты в bat/sh файл
 № | Входные данные | Ожидаемый результат
  --- | --- | ---
 T1.1 | app.jar | 1 + справка
 T1.2 | app.jar -h | 1 + справка
 T1.3 | app.jar -q | 0 + справка
 Т1.4 | app.jar 12345 | 0 + справка
 T1.5 | app.jar -res A.B 12345 | 0 + справка
 T2.1 | app.jar -login vasya -pass 123 | 0
 T2.2 | app.jar -login VASYA -pass 123 | 2
 T2.3 | app.jar -login asd -pass 123 | 3
 T2.4 | app.jar -login admin -pass 123 | 4
 T3.1 | app.jar -login vasya -pass 123 -res A -role READ | 0
 T3.2 | app.jar -login vasya -pass 123 -res A -role DELETE | 5
 T3.3 | app.jar -login vasya -pass 123 -res A -role WRITE | 6
 T3.4 | app.jar -login vasya -pass 123 -res A.B -role READ | 0
 T3.5 | app.jar -login vasya -pass 0000 -res A.B -role DELETE | 4
 T3.6 | app.jar -login admin -pass admin -res A -role EXECUTE | 6
 T3.7 | app.jar -login admin -pass admin -res A.A -role WRITE | 6
 
 #### 3. Простейшие сценарии
 1. Проверяем наличие аргументов
 2. Создаем функцию вывода справки
 3. Проверяем, надо ли выводить справку (args[0]=="-h")
 4. Во всех остальных случаях выводим справку и возвращаем 0
 
 #### 4. Аутентификация 
 1. Создаем метод надо ли аутентифицировать 
(args[0]=="-login" && args[2]=="-pass")
 2. Создаем метод проверки правильности формата логина
 3. Создаем метод проверки существования логина
     + login==vasya
 4. Создаем метод проверки пароля
     + pass==123
 5. Создаем data class User
     + login
     + pass
 6. Создаем коллекцию юзеров с тестовыми данными
 7. Исправляем методы 4.3 и 4.4 на работу с коллекцией юзеров
 8. Создаем класс ArgHandler
    + h
    + login
    + pass
    + конструктор (args:Array\<String\> )
    + нужна ли справка
    + есть ли аргументы
    + нужна ли аутентификация
 9. Рефакторинг кода, чтобы использовался ArgHandler
 
 #### 5. Авторизация
 1. Добавить в ArgHandler метод "нужна ли авторизация"
 2. Создать data class Resource
    + res
    + role
    + user
 3. Создать коллекцию ресурсов с тестовыми данными
 4. Создать метод проверки существования роли
 5. Добавляем поля *res* и *role* в ArgHandler
 6. Добавляем метод поиска ресурса по имени в класс Ресурс
 7. Добавляем метод проверки вложенности ресурса (используем find)
 8. Создать класс Session
    + user
    + role
    + res
    + time
    + vol
 9. Создать метод  
