import kotlin.system.exitProcess

class ArgHandler(val args: Array<String) {
	
	
    var login: String? = null
    var pass: String? = null

    init {
		
		if(isHelpNeeded){
			exitProcess(1) //надо возвращать в Main
		}
		
		if(args.size >= 4){
			login = args[1]
			pass = args[3]
		}
		else{
			exitProcess(1)
		}
		
		//перед получением следующих параметров проверяем ещё раз длину массива 
		
    }

    fun isHelpNeeded(): Boolean {
        if (args.isEmpty()) {
            return true
        } else if (args[0] == "-h") {
            return true	
        }
        return false
    }

	fun isAuthenticationNeeded(): Boolean = login != null && pass != null

}