package com.project.app.dao

import com.project.app.domain.Permission
import java.sql.Connection

class AuthorizationDAO(val dbConnection: Connection) {

    @Suppress("MagicNumber")
    fun getResource(resPath:String, role: String, login: String): Permission? {

        val query = """
                    SELECT * FROM permissions 
                    WHERE login =? 
                    AND role = ? 
                    AND 
                    (CONCAT(res,'.') = SUBSTRING(?,1,LENGTH(res)+1)
                    OR 
                    res = ?)
                    """

        val statement = dbConnection.prepareStatement(query)

        var out: Permission? = null

        statement.use {
            it.setString(1, login)
            it.setString(2, role)
            it.setString(3, resPath)
            it.setString(4, resPath)

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
