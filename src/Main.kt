import kotlin.system.exitProcess

fun main(args: Array<String>) {
    val businessLogic = BusinessLogic()
    if (args.isEmpty()) {
        businessLogic.printHelp()
        exitProcess(1)
    } else {
        if (args[0] == "-h") {
            businessLogic.printHelp()
            exitProcess(1)
        }
    }
    exitProcess(0)
}

fun isAuthenticationNeeded(args: Array<String>): Boolean {
    return args[0] == "-login" && args[2] == "-pass"
}