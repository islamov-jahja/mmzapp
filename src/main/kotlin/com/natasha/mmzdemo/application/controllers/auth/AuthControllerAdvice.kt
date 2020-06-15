package com.natasha.mmzdemo.application.controllers.auth

import com.natasha.mmzdemo.application.controllers.auth.exceptions.ClientExistsException
import com.natasha.mmzdemo.application.controllers.dto.ErrorResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class AuthControllerAdvice {
    @ExceptionHandler(value = [ClientExistsException::class])
    fun clientExistsException(): ResponseEntity<ErrorResponse>{
        val error = ErrorResponse(1, "данный аккаунт уже существует")
        return ResponseEntity.status(400).body(error)
    }
}