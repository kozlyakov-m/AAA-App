import kotlinx.cli.*

class ArgHandler(val args: Array<String>) {

    val parser = ArgParser("app.jar")
    
    //var h: Boolean = false
    val login by parser
            .option(ArgType.String, shortName = "login")
            .required()
    val pass by parser
            .option(ArgType.String, shortName = "pass")
            .default("")
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
        } catch (e: Exception) {
        
        }
    }
    
    /* init {

        if (args.size >= 4) {
            login = args[1]
            pass = args[3]
        }
        if (args.size >= 8) {
            res = args[5]
            role = args[7]
        }
        if (args.size >= 14) {
            ds = args[9]
            de = args[11]
            vol = args[13]


        }
    }*/

    fun isHelpRequired(): Boolean {
        if (args.isEmpty()) {
            return true
        } else if (args[0] == "-h") {
            return true
        }
        return false
    }

    fun isAuthenticationRequired(): Boolean =
            login.isNotEmpty() && pass.isNotEmpty()

    fun isAuthorizationRequired(): Boolean =
            res.isNotEmpty() && role.isNotEmpty()

    fun isAccountingRequired(): Boolean =
            ds.isNotEmpty() && de.isNotEmpty() && vol.isNotEmpty()
}
