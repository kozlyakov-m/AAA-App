package com.project.app.service

import com.project.app.ExitCodes
import com.project.app.Role
import com.project.app.domain.Permission

class AuthorizationService(private val permissions: List<Permission>) {

    var permission: Permission? = null

    fun authorization(
        resPath: String,
        role: String,
        username: String
    ): ExitCodes {

        if (!Role.isRoleExists(role)) return ExitCodes.INVALID_ROLE // возвращаем код 5, если роль не существует

        for (perm in permissions) {
            if (username != perm.username || role != perm.role) {
                continue
            }
            if (isChild(resPath, perm.resPath)) {
                permission = perm
                return ExitCodes.SUCCESS
            }
        }
        return ExitCodes.ACCESS_DENIED
    }

    private fun isChild(pathFromQuery: String, pathFromDB: String): Boolean {

        // делим по точке желаемый ресурс и ресурс из коллекции
        val query: Array<String> = pathFromQuery.split(".").toTypedArray()
        val resFromDB: Array<String> = pathFromDB.split(".").toTypedArray()

        if (query.size < resFromDB.size) { // если запрос короче чем ресурс из бд, то это не потомок
            return false
        } else { // иначе проверяем совпадение узлов по порядку (от 0 до длины ресурса из бд)

            for (i in resFromDB.indices) {
                if (resFromDB[i] != query[i]) return false
            }
            return true
        }
    }
}
