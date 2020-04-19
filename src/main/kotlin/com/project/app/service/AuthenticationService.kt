package com.project.app.service

import com.project.app.ExitCodes
import com.project.app.dao.AuthenticationDAO
import com.project.app.domain.User
import java.security.MessageDigest

class AuthenticationService(val authenticationDAO: AuthenticationDAO) {

    var user: User? = null

    fun authentication(login: String, pass: String): ExitCodes {
        if (!isLoginValid(login)) {
            return ExitCodes.INVALID_LOGIN
        }
        // check that user exists in db
        user = authenticationDAO.findUser(login) ?: return ExitCodes.USER_NOT_FOUND

        if (!checkPassword(pass, user!!)) {
            return ExitCodes.WRONG_PASSWORD
        }
        return ExitCodes.SUCCESS
    }

    private fun isLoginValid(login: String): Boolean = "^[a-z]{1,10}$".toRegex().matches(login)

/*    private fun findUser(login: String, users: List<User>): User? {
        for (user in users) {
            if (user.login == login) {
                return user
            }
        }
        return null
    }*/

    private fun checkPassword(pass: String, user: User) = pass.getHash(user.salt) == user.hash

    // stackoverflow driven development
    private fun String.getHash(
        salt: String,
        algorithm: String = "SHA-256"
    ): String {
        //val bytes = (salt + this).toByteArray()
        val bytes = (this + salt).toByteArray()
        val md = MessageDigest.getInstance(algorithm)
        val digest = md.digest(bytes)
        return digest.fold("", { str, it -> str + "%02x".format(it) })
    }
}
