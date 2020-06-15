package com.natasha.mmzdemo.application.controllers.auth.dto

data class JWTRequest(val username: String,
                      val password: String) {
}