import kotlin.system.exitProcess

fun main(args: Array<String>) {
    if (args.size == 0){
        exitProcess(1)
    }
    exitProcess(0)
}