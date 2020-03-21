import kotlin.system.exitProcess

class BusinessLogic(val args: ArgHandler) {



    fun printHelp() {
        println(
                """
                -h
                -login
                -pass
                """.trimIndent()
        )
    }

    private fun isLoginValid(login: String): Boolean {
        val pattern = "^[a-z]{1,10}$".toRegex()
        return pattern.matches(login)
    }

    private fun findUser(login: String, users: List<User>): User? {
        for (user in users) {
            if (user.login == login) {
                return user
            }
        }
        return null
    }

    private fun checkPassword(pass: String, user: User): Boolean {
        return pass == user.pass
    }

    fun authentication(login: String, pass: String): User {
        if (!isLoginValid(login)) {
            exitProcess(2)
        }
        // check that user exists in db
        val user = findUser(login, users) ?: exitProcess(3)

        if (!checkPassword(pass, user)) {
            exitProcess(4)
        }
		
		return user
    }
	
	private fun isResValid(arg: String): Boolean = TODO()
	
    private fun isRoleExists() = true //TODO
    
	fun authorization(resPath: String, role: String, username: String):Permission? {
		
		
		//делим по точке желаемый ресурс
		//делим по точке ресурс из коллекции
		
		//сравниваем полученные массивы
		//пример
		//хотим:
		//A.B.C (массив: [A, B, C])
		//есть в базе:
		//A.B (массив: [A, B]) (доступ разрешен)
		//A.C (массив: [A, C]) (доступ запрещен)
		//A.B.C.D (массив: [A, B, C, D] (доступ запрещен)
		
        if(!isRoleExists()) exitProcess(5)//TODO
        
		for(permission in permissions) {
        
            if(username == permission.username && role == permission.role){
                
                if(resPath == permission.resPath) {
                    return permission
                }
            }
            
		}
        return null
	}
	
}