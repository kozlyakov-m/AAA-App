package com.project.app.service

import com.google.inject.Inject
import com.project.app.ExitCodes
import com.project.app.Role
import com.project.app.dao.AuthorizationDAO
import com.project.app.domain.Permission

class AuthorizationService @Inject constructor(private val authorizationDAO: AuthorizationDAO) {

    var permission: Permission? = null

    fun authorization(
        resPath: String,
        role: String,
        username: String
    ): ExitCodes {

        if (!Role.isRoleExists(role)) return ExitCodes.INVALID_ROLE

        permission = authorizationDAO.getResource(resPath, role, username)

      return if (permission != null)
            ExitCodes.SUCCESS
        else
            ExitCodes.ACCESS_DENIED
    }
}
