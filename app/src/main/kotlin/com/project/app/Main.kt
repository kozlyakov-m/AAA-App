package com.project.app

import com.project.app.dao.AccountingDAO
import com.project.app.dao.AuthenticationDAO
import com.project.app.dao.AuthorizationDAO
import com.project.app.service.AccountingService
import com.project.app.service.AuthenticationService
import com.project.app.service.AuthorizationService
import com.project.app.ExitCodes
import java.sql.Connection
import java.sql.DriverManager
import kotlin.system.exitProcess
import org.flywaydb.core.Flyway


fun main(args: Array<String>) {

    val flyway = Flyway.configure()
            .dataSource("jdbc:h2:./AAA-App", "sa", "")
            .locations("filesystem:db")
            .load()
    flyway.migrate()

    val dbConnection: Connection = DriverManager
            .getConnection("jdbc:h2:./AAA-App", "sa", "")

    var exitCode = ExitCodes.SUCCESS
    dbConnection.use {
        val argHandler = ArgHandler(args)

        val authenticationDAO = AuthenticationDAO(dbConnection)
        val authenticationService = AuthenticationService(authenticationDAO)

        val authorizationDAO = AuthorizationDAO(dbConnection)
        val authorizationService = AuthorizationService(authorizationDAO)

        val accountingDAO = AccountingDAO(dbConnection)
        val accountingService = AccountingService(accountingDAO)

        val app = Application(argHandler, authenticationService, authorizationService, accountingService)
        exitCode = app.start()
    }

    exitProcess(exitCode.code)
}
