package com.project.app

import com.google.inject.Guice
import com.project.app.service.AccountingService
import com.project.app.service.AuthenticationService
import com.project.app.service.AuthorizationService
import org.flywaydb.core.Flyway
import kotlin.system.exitProcess


fun main(args: Array<String>) {

    val flyway = Flyway.configure()
            .dataSource("jdbc:h2:./AAA-App", "sa", "")
            .locations("filesystem:db")
            .load()
    flyway.migrate()

    val injector = Guice.createInjector(GuiceConnectionModule())

    val argHandler = ArgHandler(args)

    val authenticationService = injector.getInstance(AuthenticationService::class.java)

    val authorizationService = injector.getInstance(AuthorizationService::class.java)
    val accountingService = injector.getInstance(AccountingService::class.java)

    //val app = injector.getInstance(Application::class.java)
    val app = Application(argHandler, authenticationService, authorizationService, accountingService)

    exitProcess(app.start().code)
}
