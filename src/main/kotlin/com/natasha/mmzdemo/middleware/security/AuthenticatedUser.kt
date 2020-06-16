package com.natasha.mmzdemo.middleware.security

import com.natasha.mmzdemo.infrastructure.models.Role
import org.springframework.stereotype.Component

@Component
class AuthenticatedUser {
    lateinit var userName: String
    lateinit var role: Role
}