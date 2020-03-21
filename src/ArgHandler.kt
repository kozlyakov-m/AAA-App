import kotlin.system.exitProcess

class ArgHandler(val args: Array<String>) {
    var h: Boolean = false
    var login: String = ""
    var pass: String = ""
    var res: String = ""
    var role: String = ""
    var ds: String = ""
    var de: String = ""

    init {
        /*if (isHelpRequired()) {
            exitProcess(1) //надо возвращать в Main
        }*/
        if (args.size >= 4) {
            login = args[1]
            pass = args[3]
        }
        if (args.size >= 8) {
            res = args[5]
            role = args[7]
        }
        if (args.size >= 12) {
            ds = args[9]
            de = args[11]
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
	
    fun isAuthenticationRequired(): Boolean = !login.isEmpty() && !pass.isEmpty()

	fun isAuthorizationRequired(): Boolean = !res.isEmpty() && !role.isEmpty()
	
	
}
