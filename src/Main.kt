import kotlin.system.exitProcess

fun main(args: Array<String>) {
    val buisnessLogic = BuisnessLogic()
    if (args.isEmpty()) {
        buisnessLogic.printHelp()
        exitProcess(1)
    } else {
        if (args[0] == "-h"){
            buisnessLogic.printHelp()
            exitProcess(1)
        }
    }
    exitProcess(0)
}