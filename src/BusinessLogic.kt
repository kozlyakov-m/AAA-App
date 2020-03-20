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

    fun loginExist(login: String): Boolean {
        return login == "vasya"
    }

    fun checkPassword(pass: String): Boolean {
        return pass == "123"
    }
}