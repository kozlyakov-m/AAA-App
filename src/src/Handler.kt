class Handler {
    val arguments: Array<String>

    init {
        this.arguments = arguments
    }

    fun handler(args: Array<String>) {
        /*
        * Method which splits args into certain positions:
        * account, password, role, resourse*/
        for(int i=0, i < args.length, i++) {
            when(args[i]) {
                "-login" -> User.checkAcc(args[i+1])
                "-pass"  -> User.checkPassword(args[i+1])
                "-res"   -> User.checkRes(args[i+1])
                "-role"  -> User.checkRole(args[i+1])
            }
        }
    }
}