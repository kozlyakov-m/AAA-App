1) Cоздаём проект и прикручиваем его к удалённому репозиторию на GitHub  
2) Создаём класс Handler  
    2.1) Cоздаём метод handler внутри класса Handler для обработки аргументов командной строки  
    2.2) Создаём метод printHelp внутри класса Handler для вывода справки  
3) Создаём класс User, имеющий в конструкторе логин, пароль, роль  
    3.1) Создаём метод checkLogin   
    3.2) Создаём метод checkPassword  
    3.3) Создаём метод для получения хэша входного пароля  
    3.4) Создаём метод для получения хэша из БД  
    3.5) Создаём метод checkRole
4) Создаём класс Session, имеющий в конструкторе ресурс, дата начала и конца и объём  
    4.1) Создаём метод checkRes  
    4.2) Создаём метод timer, для отслеживания проведённого времени с временем конца  
    4.3) Создаём метод storeVolume, для хранения объёма  
5) Усложняем логику работу приложения  
    5.1) Создаём класс Codes который содержит все возможные коды ошибок  
    5.2) К уже имеющимся функциям "прикручиваем" коды
