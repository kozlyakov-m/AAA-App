package com.project.app.dao

import com.google.inject.Inject
import com.project.app.domain.User
import java.sql.Connection

class AuthenticationDAO @Inject constructor(private val dbConnection: Connection) {
    fun findUser(login: String): User? {
        val query = "SELECT * FROM users WHERE login = ?"
        val statement = dbConnection.prepareStatement(query)
        var user: User? = null
        statement.use {
            it.setString(1, login)
            val result = statement.executeQuery()
            if (result.next()) {
                user = User(
                        result.getString("login"),
                        result.getString("hash"),
                        result.getString("salt")
                )
            }
        }
        return user
    }
}
