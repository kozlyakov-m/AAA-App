import kotlin.system.exitProcess

// global value!!
val users: List<User> = listOf(
        User("vasya", "123"),
        User("admin", "admin"),
        User("q", "?!#"),
        User("abcdefghij", "pass")
)

val permissions: List<Permission> = listOf(
	Permission("A", "READ", "vasya")
    Permission("A.B.C", "WRITE", "vasya")
    Permission("A.B", "EXECUTE", "admin")
    Permission("A", "READ", "admin")
    Permission("A.B", "WRITE", "admin")
    Permission("A.B.C", "READ", "admin")
    Permission("B", "EXECUTE", "q")
)


fun main(args: Array<String>) {
    val argHandler = ArgHandler(args)
    val businessLogic = BusinessLogic(argHandler)
	
	if(argHandler.isHelpRequired()) {
		businessLogic.printHelp()
	}
	
	val user: User = (
		if(argHandler.isAuthenticationRequired()) {
			businessLogic.authentication(argHandler.login, argHandler.pass)
		}
		else {
			exitProcess(1)
		}
	)
	/*if(argHandler.isAuthorizationRequired()) {
		businessLogic.authorization()
	}*/
	
	exitProcess(0)	
}
	
	