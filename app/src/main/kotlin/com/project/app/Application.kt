package com.project.app

import com.google.inject.Inject
import com.project.app.domain.Permission
import com.project.app.service.AccountingService
import com.project.app.service.AuthenticationService
import com.project.app.service.AuthorizationService

class Application @Inject constructor(
    private val argHandler: ArgHandler,
    private val authenticationService: AuthenticationService,
    private val authorizationService: AuthorizationService,
    private val accountingService: AccountingService
) {

    fun start(): ExitCodes {

        var exitCode: ExitCodes = if (argHandler.isAuthenticationRequired()) {
            authenticationService.authentication(argHandler.login!!, argHandler.pass!!)
        } else {
            ExitCodes.HELP
        }
        if (exitCode != ExitCodes.SUCCESS) return exitCode

        val user = authenticationService.user!! // если user null, программа завершится раньше

        exitCode = if (argHandler.isAuthorizationRequired()) {
            authorizationService.authorization(argHandler.res, argHandler.role, user.login)
        } else {
            return ExitCodes.SUCCESS // 0 так как аутентификация прошла успешно
        }
        if (exitCode != ExitCodes.SUCCESS) return exitCode

        val permission: Permission = authorizationService.permission!!

        exitCode = if (argHandler.isAccountingRequired()) {
            accountingService.accounting(
                    permission,
                    argHandler.ds,
                    argHandler.de,
                    argHandler.vol
            )
        } else {
            return ExitCodes.SUCCESS
        }
        return exitCode
    }
}
