package com.dinosaur.app

import com.dinosaur.app.domain.Permission
import com.dinosaur.app.service.AccountingService
import com.dinosaur.app.service.AuthenticationService
import com.dinosaur.app.service.AuthorizationService
import kotlin.system.exitProcess
import java.sql.*

fun main(args: Array<String>) {

    val conn: Connection = DriverManager.
            getConnection("jdbc:h2:./test", "sa", "");

    val q = "CREATE TABLE users (id int, login varchar(255))"
    val q1 = "INSERT INTO users VALUES(1, 'name1')"
    val q2 = "SELECT * FROM users"
    val stmt = conn.createStatement()
//    val res = stmt.execute(q)
//    val res1 = stmt.execute(q1)
    val res2 = stmt.executeQuery(q2)
    res2.next()
    println(res2.getString("id"))
    conn.close()

    val argHandler = ArgHandler(args)
    val authenticationService = AuthenticationService(users)

    var exitCode: ExitCodes = if (argHandler.isAuthenticationRequired()) {
        authenticationService.authentication(argHandler.login!!, argHandler.pass!!)
    } else {
        ExitCodes.HELP
    }
    if (exitCode != ExitCodes.SUCCESS) exitProcess(exitCode.code)

    val user = authenticationService.user!! //если user null, программа завершится раньше

    // if authentication passed create instance of AuthorizationService
    val authorizationService = AuthorizationService(permissions)
    exitCode = if (argHandler.isAuthorizationRequired()) {
        authorizationService.authorization(argHandler.res, argHandler.role, user.login)
    } else {
        exitProcess(ExitCodes.SUCCESS.code) //0 так как аутентификация прошла успешно
    }
    if (exitCode != ExitCodes.SUCCESS) exitProcess(exitCode.code)

    val permission: Permission = authorizationService.permission!!

    // if authorization passed create instance of AccountingService
    val accountingService = AccountingService(sessions)
    exitCode = if (argHandler.isAccountingRequired()) {
        accountingService.accounting(
                permission,
                argHandler.ds,
                argHandler.de,
                argHandler.vol
        )

    } else {
        exitProcess(ExitCodes.SUCCESS.code)
    }

    if (exitCode != ExitCodes.SUCCESS) exitProcess(exitCode.code)

    sessions.add(accountingService.session!!)
    exitProcess(exitCode.code)
}