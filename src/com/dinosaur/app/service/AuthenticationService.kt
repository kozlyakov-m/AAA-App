package com.dinosaur.app.service

import com.dinosaur.app.domain.User
import com.dinosaur.app.users
import java.security.MessageDigest
import kotlin.system.exitProcess

class AuthenticationService {
    fun authentication(login: String, pass: String): User {
        if (!isLoginValid(login)) {
            exitProcess(2)
        }
        // check that user exists in db
        val user = findUser(login, users) ?: exitProcess(3)

        if (!checkPassword(pass, user)) {
            exitProcess(4)
        }

        return user
    }

    private fun isLoginValid(login: String): Boolean {
        val pattern = "^[a-z]{1,10}$".toRegex()
        return pattern.matches(login)
    }

    private fun findUser(login: String, users: List<User>): User? {
        for (user in users) {
            if (user.login == login) {
                return user
            }
        }
        return null
    }

    private fun checkPassword(pass: String, user: User): Boolean {
        return pass.getHash(user.salt) == user.hash
    }

    //stackoverflow driven development
    private fun String.getHash(salt: String, algorithm: String = "SHA-512"): String {
        val bytes = (salt + this).toByteArray()
        val md = MessageDigest.getInstance(algorithm)
        val digest = md.digest(bytes)
        return digest.fold("", { str, it -> str + "%02x".format(it) })
    }
}