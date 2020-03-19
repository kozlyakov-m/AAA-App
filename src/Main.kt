import kotlin.system.exitProcess

fun main(args: Array<String>) {
    val buisnessLogic = BuisnessLogic()
    if (args.size == 0) {
        buisnessLogic.printHelp()
        exitProcess(1)
    }
    exitProcess(0)
}