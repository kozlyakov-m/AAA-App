package com.dinosaur.app

enum class Role {
    READ,
    WRITE,
    EXECUTE;

    companion object {
        fun isRoleExists(role: String): Boolean = Role.values().map { it.name }.contains(role)
    }
}
