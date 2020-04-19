package com.project.app.domain

data class User(val login: String, val hash: String, val salt: String)
