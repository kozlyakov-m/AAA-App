package com.project.app

import com.project.app.dao.AccountingDAO
import com.project.app.dao.AuthenticationDAO
import com.project.app.dao.AuthorizationDAO
import com.project.app.domain.Permission
import com.project.app.service.AccountingService
import com.project.app.service.AuthenticationService
import com.project.app.service.AuthorizationService

import kotlin.system.exitProcess
import org.flywaydb.core.Flyway
import java.sql.Connection
import java.sql.DriverManager

fun main(args: Array<String>) {

    val flyway = Flyway.configure()
            .dataSource("jdbc:h2:./AAA-App", "sa", "")
            .locations("filesystem:db")
            .load()
    flyway.migrate()

    val dbConnection: Connection =  DriverManager.
            getConnection("jdbc:h2:./AAA-App", "sa", "")

    dbConnection.use {
        val argHandler = ArgHandler(args)

        val authenticationDAO = AuthenticationDAO(dbConnection)
        val authenticationService = AuthenticationService(authenticationDAO)

        var exitCode: ExitCodes = if (argHandler.isAuthenticationRequired()) {
            authenticationService.authentication(argHandler.login!!, argHandler.pass!!)
        } else {
            ExitCodes.HELP
        }
        if (exitCode != ExitCodes.SUCCESS) exitProcess(exitCode.code)

        val user = authenticationService.user!! // если user null, программа завершится раньше

        // if authentication passed create instance of AuthorizationService
        val authorizationDAO = AuthorizationDAO(dbConnection)
        val authorizationService = AuthorizationService(authorizationDAO)
        exitCode = if (argHandler.isAuthorizationRequired()) {
            authorizationService.authorization(argHandler.res, argHandler.role, user.login)
        } else {
            exitProcess(ExitCodes.SUCCESS.code) // 0 так как аутентификация прошла успешно
        }
        if (exitCode != ExitCodes.SUCCESS) exitProcess(exitCode.code)

        val permission: Permission = authorizationService.permission!!

        // if authorization passed create instance of AccountingService
        val accountingDAO = AccountingDAO(dbConnection)
        val accountingService = AccountingService(accountingDAO)
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

        exitProcess(exitCode.code)
    }
}
