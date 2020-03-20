import kotlin.system.exitProcess

class BusinessLogic {

    fun printHelp() {
        println(
                """
                -h
                -login
                -pass
                """.trimIndent()
        )
    }

    fun isLoginValid(login: String): Boolean {
        val regex = "^[a-z]{1,10}$".toRegex()
        return regex.matches(login)
    }

    fun findUser(login: String, users: List<User>): User? {
        for (user in users) {
            if (user.login == login) {
                return user
            }
        }
        return null
    }

    fun checkPassword(pass: String, user: User): Boolean {
        return pass == user.pass
    }

    fun authentication(login: String, pass: String) {
        if (!isLoginValid(login)) {
            exitProcess(2)
        }
        val user = findUser(login, users) ?: exitProcess(3)

        if (!checkPassword(pass, user)) {
            exitProcess(4)  
        }
    }
}