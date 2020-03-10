# План выполнения первого набора требований

#### 0. Подготовительные шаги
0.1. Включить toggl
0.2. Прочитать требования
0.3. Создать репозиторий
0.4. Создать пустой проект в Idea

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
 3.1. Проверяем наличие аргументов
 3.2. Создаем функцию вывода справки
 3.3. Если (args[0]=="-h"), то выводим справку и возвращаем 1
 3.4. Иначе выводим справку и возвращаем 0
 
 #### 4. Аутентификация 
 4.1. Создаем метод надо ли аутентифицировать 
(args[0]=="-login" && args[2]=="-pass")
 4.2. Создаем метод проверки правильности формата логина
 4.3. Создаем метод проверки существования логина
     + login==vasya
 4.4. Создаем метод проверки пароля
     + pass==123
 4.5. Создаем data class User
     + login
     + pass
 4.6. Создаем коллекцию юзеров с тестовыми данными
 4.7. Исправляем методы 4.3 и 4.4 на работу с коллекцией юзеров
 4.8. Создаем класс ArgHandler
    + h
    + login
    + pass
    + конструктор (args:Array\<String\> )
    + нужна ли справка
    + есть ли аргументы
    + нужна ли аутентификация
 4.9. Рефакторинг кода, чтобы использовался ArgHandler
 
 #### 5. Авторизация
 5.1. Добавить в ArgHandler метод "нужна ли авторизация"
 5.2. Создать data class Resource
    + res
    + role
    + user
 5.3. Создать коллекцию ресурсов с тестовыми данными
 5.4. Создать метод проверки существования роли
 5.5. Добавляем поля *res* и *role* в ArgHandler
 5.6. Добавляем метод поиска ресурса по имени в класс Ресурс
 5.7. Добавляем метод проверки вложенности ресурса (используем find)
 5.8. Создать класс Session
    + user
    + role
    + res
    + time
    + vol
 5.9. Создать метод  
