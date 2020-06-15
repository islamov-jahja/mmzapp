package com.natasha.mmzdemo.infrastructure.helpers

import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.stereotype.Component

@Component
class PasswordProcessor {
    private val salt: String = "0392410801gtr23t2512"

    fun getHashOfPassword(password: String): String{
        return BCrypt.hashpw(password, BCrypt.gensalt())
    }

    fun passMatch(hash: String, data: String): Boolean
    {
        return BCrypt.checkpw(data, hash)
    }
}