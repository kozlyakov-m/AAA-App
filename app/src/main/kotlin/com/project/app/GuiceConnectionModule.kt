package com.project.app

import com.google.inject.AbstractModule
import com.project.app.dao.AuthenticationDAO
import java.sql.Connection

class GuiceConnectionModule: AbstractModule() {
    override fun configure(){
        bind(Connection::class.java).toProvider(ConnectionProvider::class.java)

    }
}
