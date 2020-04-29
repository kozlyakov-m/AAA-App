package com.project.app

import com.google.inject.Provider
import java.sql.Connection
import java.sql.DriverManager

class ConnectionProvider: Provider<Connection> {
    override fun get(): Connection {
        return DriverManager.getConnection(
                "jdbc:h2:./AAA-App",
                "sa",
                ""
        )
    }

}