package com.natasha.mmzapp.infrastructure.helpers

import com.natasha.mmzapp.application.controllers.JerseyConfig
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.stereotype.Component
import java.io.FileInputStream

@Component
class PasswordProcessor {
    private val salt: String = "0392410801gtr23t2512"

    fun getHashOfPassword(password: String): String{
        return BCrypt.hashpw(password, salt)
    }

    fun passMatch(hash: String, data: String): Boolean
    {
        return BCrypt.checkpw(data, hash)
    }
}