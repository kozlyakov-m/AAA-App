import kotlinx.cli.*
import java.lang.IllegalStateException

class ArgHandler(private val args: Array<String>) {

    private val parser = ArgParser("app.jar")

    //var h: Boolean = false
    val login: String? by parser
            .option(ArgType.String, shortName = "login")

    val pass: String? by parser
            .option(ArgType.String, shortName = "pass")

    val res by parser
            .option(ArgType.String, shortName = "res")
            .default("")

    val role by parser
            .option(ArgType.String, shortName = "role")
            .default("")

    val ds by parser
            .option(ArgType.String, shortName = "ds")
            .default("")

    val de by parser
            .option(ArgType.String, shortName = "de")
            .default("")

    val vol by parser
            .option(ArgType.String, shortName = "vol")
            .default("") //will convert to int in BusinessLogic

    init {
        try {
            parser.parse(args)
        } catch (e: IllegalStateException){
            println(e.message)
        }
    }

    fun isHelpRequired(): Boolean {
        if (args.isEmpty()) {
            return true
        } else if (args[0] == "-h") {
            return true
        }
        return false
    }

    fun isAuthenticationRequired(): Boolean =
            !login.isNullOrEmpty() && !pass.isNullOrEmpty()

    fun isAuthorizationRequired(): Boolean =
            res.isNotEmpty() && role.isNotEmpty()

    fun isAccountingRequired(): Boolean =
            ds.isNotEmpty() && de.isNotEmpty() && vol.isNotEmpty()
}
