class User {

    val account: String
    val password: String

    init {
        /*
        * Set */
        this.account = account
        this.password = password
    }

    fun checkAcc(account: String) {
        /*
        * Method which checks accs in base */
        this.checkAccFormat(account)
    }

    fun checkAccFormat(account: String) {
        /*
        * Method which checks account length and characters ad them
        * register in account string*/
        re.compile(r'[a-z]{1-10}')

    }

    fun checkPassword(password: String) {
        /*
        * Method which generates hash of string, gets hash from base
        * and compare these two hashes*/
        this.generateHash(password)
        this.getPassHashFromBase(account)
    }

}