package com.dinosaur.app

import com.dinosaur.app.domain.Permission
import com.dinosaur.app.domain.Session
import com.dinosaur.app.domain.User
import com.dinosaur.app.service.AccountingService
import com.dinosaur.app.service.AuthenticationService
import com.dinosaur.app.service.AuthorizationService
import kotlin.system.exitProcess

fun main(args: Array<String>) {

    val argHandler = ArgHandler(args)
    val authenticationService = AuthenticationService(users)

    val exitCode: ExitCodes = if (argHandler.isAuthenticationRequired()) {
        authenticationService.authentication(argHandler.login!!, argHandler.pass!!)
    } else {
        ExitCodes.HELP
    }
    if(exitCode != ExitCodes.SUCCESS) exitProcess(exitCode.code)

    val user = authenticationService.user!! //если user null, программа завершится раньше

    // if authentication passed create instance of AuthorizationService
    val authorizationService = AuthorizationService(permissions)
    val permission: Permission = if (argHandler.isAuthorizationRequired()) {
        authorizationService.authorization(argHandler.res, argHandler.role, user.login) ?: exitProcess(6)
    } else {
        exitProcess(0) //0 так как аутентификация прошла успешно
    }

    // if authorization passed create instance of AccountingService
    val accountingService = AccountingService(sessions)
    val session: Session = if (argHandler.isAccountingRequired()) {
        accountingService.accounting(
                permission,
                argHandler.ds,
                argHandler.de,
                argHandler.vol
        ) ?: exitProcess(7)
    } else {
        exitProcess(0)
    }

    sessions.add(session)
    exitProcess(0)
}
