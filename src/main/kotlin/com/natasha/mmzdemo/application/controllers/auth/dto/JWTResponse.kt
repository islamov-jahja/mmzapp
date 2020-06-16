package com.natasha.mmzdemo.application.controllers.auth.dto

import com.natasha.mmzdemo.infrastructure.models.Role

data class JWTResponse(val jwtToken: String,
                       val role: Role) {
}