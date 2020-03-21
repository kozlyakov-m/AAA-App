import kotlin.system.exitProcess

class ArgHandler(val args: Array<String>, val businessLogic: BusinessLogic) {

    var login: String = ""
    var pass: String = ""

    init {
        if (isHelpNeeded()) {
            businessLogic.printHelp()
            exitProcess(1)
        } else if (isAuthenticationNeeded()) {
            login = args[1]
            pass = args[3]
            // if login or pass is not correct program will exit
            businessLogic.authentication(login, pass)
        } else if (isAutorizationNeeded()) {
            // check that autorization neccessary, if not - exit
        } else {

        }
    }

    fun isHelpNeeded(): Boolean {
        if (args.isEmpty()) {
            return true
        } else if (args[0] == "-h") {
            return true
        }
        return false
    }

    fun isAuthenticationNeeded(): Boolean {
        if (args[0] == "-login" && args[2] == "-pass" ) {
            return true
        } else {
            businessLogic.printHelp()
            exitProcess(0)
        }
    }


}