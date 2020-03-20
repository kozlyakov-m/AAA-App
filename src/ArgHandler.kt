import kotlin.system.exitProcess

class ArgHandler(val args: Array<String>, val businessLogic: BusinessLogic) {

    var login: String = ""
    var pass: String = ""

    init {
        if(isHelpNeeded()){
            businessLogic.printHelp()
            exitProcess(1)
        }
        else{
            login = args[1]
            pass = args[3]

        }
    }

    fun isHelpNeeded(): Boolean {
        if (args.isEmpty()) {
            return true
        } else {
            if (args[0] == "-h") {
                return true
            }
        }
        return false
    }

    fun isAuthenticationNeeded(): Boolean {
        return args[0] == "-login" && args[2] == "-pass"
    }


}