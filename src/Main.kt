import kotlin.system.exitProcess

val users: List<User> = listOf(
        User("vasya", "123"),
        User("admin", "admin"),
        User("q", "?!#"),
        User("abcdefghij", "pass")
)

fun main(args: Array<String>) {
    val businessLogic = BusinessLogic()
    val argHandler = ArgHandler(args, businessLogic)
    argHandler.isHelpNeeded()
    if (argHandler.isAuthenticationNeeded()) {
        businessLogic.authentication(argHandler.login, argHandler.pass)
    }
    exitProcess(0)
}