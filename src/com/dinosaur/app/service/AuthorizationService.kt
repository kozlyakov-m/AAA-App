package com.dinosaur.app.service

import com.dinosaur.app.Role
import com.dinosaur.app.domain.Permission
import kotlin.system.exitProcess

class AuthorizationService(private val permissions: List<Permission>) {

    fun authorization(
            resPath: String,
            role: String,
            username: String
    ): Permission? {

        if (!Role.isRoleExists(role)) exitProcess(5) //возвращаем код 5, если роль не существует
        //возможно, перенести в Main

        for (permission in permissions) {
            if (username != permission.username || role != permission.role) {
                continue
            }
            if (isChild(resPath, permission.resPath)) {
                return permission
            }
        }
        return null
    }

    private fun isChild(pathFromQuery: String, pathFromDB: String): Boolean {

        //делим по точке желаемый ресурс и ресурс из коллекции
        val query: Array<String> = pathFromQuery.split(".").toTypedArray()
        val resFromDB: Array<String> = pathFromDB.split(".").toTypedArray()

        if (query.size < resFromDB.size) { //если запрос короче чем ресурс из бд, то это не потомок
            return false
        } else { //иначе проверяем совпадение узлов по порядку (от 0 до длины ресурса из бд)

            for (i in resFromDB.indices) {
                if (resFromDB[i] != query[i]) return false
            }
            return true
        }
    }
}