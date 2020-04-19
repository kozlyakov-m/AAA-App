package com.project.app.dao

import com.project.app.Role
import com.project.app.domain.Permission
import java.sql.Connection

class AuthorizationDAO (val dbConnection: Connection) {
    fun getResource(login: String, role: String, nodes: List<String>): Permission? {

        val builder = StringBuilder()
        builder.append(
                "SELECT * FROM permissions WHERE  login = ? AND role = ? AND (res = ?"
        )
        for (index in 1 until nodes.size) { //-1 because one "resource = ?" is already in query
            builder.append(" OR res = ?")
        }
        builder.append(")")
        val query = builder.toString()

        val statement = dbConnection.prepareStatement(query)

        var out: Permission? = null

        statement.use {
            it.setString(1, login)
            it.setString(2, role)
            for (index in nodes.indices) {
                val currentNode = nodes.subList(0, index + 1).joinToString(".")
                it.setString(index + 3, currentNode)
                //+3 because prepared statement numeration starts from 1 and 1st, 2nd is already taken
            }

            val result = statement.executeQuery()
            if (result.next()) {
                out = Permission(
                        result.getInt("id"),
                        result.getString("res"),
                        result.getString("role"),
                        result.getString("login")
                )

            }
        }
        return out
    }
}