import kotlin.system.exitProcess

class ArgHandler(val args: Array<String>) {
    var h: Boolean = false
    var login: String = ""
    var pass: String = ""
    var res: String = ""
    var role: String = ""
    var ds: String = ""
    var de: String = ""
    var vol: String = "" //will convert to int in BusinessLogic

    init {

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
	
	fun isAccountingRequired(): Boolean = !ds.isEmpty() && !de.isEmpty() && !vol.isEmpty()
}
