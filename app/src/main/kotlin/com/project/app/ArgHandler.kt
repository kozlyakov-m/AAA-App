package com.project.app

import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType

class ArgHandler(args: Array<String>) {

    private val parser = ArgParser(
            "app.jar",
            true,
            ArgParser.OptionPrefixStyle.LINUX,
            false
    )

    val login: String? by parser
            .option(ArgType.String, null, "login", null, null)

    val pass: String? by parser
            .option(ArgType.String, null, "pass", null, null)

    val _res: String? by parser
            .option(ArgType.String, null, "res", null, null)
    val res: String
        get() = _res!!

    val _role: String? by parser
            .option(ArgType.String, null, "role", null, null)
    val role: String
        get() = _role!!

    val _ds: String? by parser
            .option(ArgType.String, null, "ds", null, null)
    val ds: String
        get() = _ds!!

    val _de: String? by parser
            .option(ArgType.String, null, "de", null, null)
    val de: String
        get() = _de!!

    val _vol: String? by parser
            .option(ArgType.String, null, "vol", null, null) // will convert to int in BusinessLogic
    val vol: String
        get() = _vol!!

    init {
        try {
            parser.parse(args)
        } catch (e: IllegalStateException) {
            println(e.message)
            // exitProcess(0) //пройдут тесты в первоначальном варианте
        }
    }

    fun isAuthenticationRequired(): Boolean =
            !login.isNullOrEmpty() && !pass.isNullOrEmpty()

    fun isAuthorizationRequired(): Boolean =
            !_res.isNullOrEmpty() && !_role.isNullOrEmpty()

    fun isAccountingRequired(): Boolean =
            !_ds.isNullOrEmpty() && !_de.isNullOrEmpty() && !_vol.isNullOrEmpty()
}
