package com.dinosaur.app

enum class ExitCodes(val code: Int){
    SUCCESS(0),
    HELP(1),
    INVALID_LOGIN(2),
    WRONG_LOGIN(3),
    WRONG_PASSWORD(4),
    INVALID_ROLE(5),
    ACCESS_DENIED(6),
    INVALID_ACTIVITY(7)
}