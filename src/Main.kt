import kotlin.system.exitProcess

// global value!!
val users: List<User> = listOf(
        User("vasya", "123"),
        User("admin", "admin"),
        User("q", "?!#"),
        User("abcdefghij", "pass")
)

//запрос: A.B.D READ admin

val permissions: List<Permission> = listOf(
		Permission("A", "READ", "vasya"),
		Permission("A.B.C", "WRITE", "vasya"),
		Permission("A.B", "EXECUTE", "admin"),
		Permission("A", "READ", "admin"),
		Permission("A.B", "WRITE", "admin"),
		Permission("A.B.C", "READ", "admin"),
		Permission("B", "EXECUTE", "q")
)

val roles: Set<String> = setOf(
		"READ",
		"WRITE",
		"EXECUTE"
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
		} else {
			exitProcess(1)
		}
	)
    
    /*if(!businessLogic.isRoleExists(argHandler.role)){
        exitProcess(5)
    }*/
	
	val permission: Permission = (
        if(argHandler.isAuthorizationRequired()) {
            businessLogic.authorization(argHandler.res, argHandler.role, user.login) ?: exitProcess(6)
        } else {
            exitProcess(0) //0 так как аутентификация прошла успешно
        }
    )
    
    val session: Session = (
        if(argHandler.isAccountingRequired()) {
            businessLogic.accounting(permission, argHandler.ds, argHandler.de, argHandler.vol) ?: exitProcess(7)
        } else {
            exitProcess(0)
        }
    )
}
	
	