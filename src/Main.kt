// global value!!
val users: List<User> = listOf(
        User("vasya", "123"),
        User("admin", "admin"),
        User("q", "?!#"),
        User("abcdefghij", "pass")
)

fun main(args: Array<String>) {
    val argHandler = ArgHandler(args)
    val businessLogic = BusinessLogic(argHandler)
	
	if(argHandler.isHelpRequired) {
		businessLogic.printHelp()
	}
	
	if(argHandler.isAuthenticationRequired()) {
		businessLogic.authentication(argHandler.login, argHandler.pass)
	}
	
	
	
	