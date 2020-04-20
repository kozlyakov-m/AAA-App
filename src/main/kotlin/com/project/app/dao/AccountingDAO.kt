package com.project.app.dao

import com.project.app.domain.Permission
import java.sql.Connection

class AccountingDAO(val dbConnection: Connection) {
    fun writeSession(res: Permission, ds: String, de: String, vol: Int) {
        val query = "INSERT INTO sessions (login, res, role, ds, de, vol) VALUES (?, ?, ?, ?, ?, ?)"
        val statement = dbConnection.prepareStatement(query)
        statement.use {
            statement.setString(1, res.username)
            statement.setString(2, res.resPath)
            statement.setString(3, res.role)
            statement.setString(4, ds)
            statement.setString(5, de)
            statement.setInt(6, vol)
        }
    }
}