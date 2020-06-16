package com.natasha.mmzdemo.application.controllers.application

import com.natasha.mmzdemo.application.controllers.application.exceptions.ApplicationNotFoundException
import com.natasha.mmzdemo.application.controllers.application.exceptions.ListSiNotForApplicationNotFound
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ApplicationControllerAdvice {
    @ExceptionHandler(value = [ListSiNotForApplicationNotFound::class, ApplicationNotFoundException::class])
    fun notFoundException(): ResponseEntity<Any>{
        return ResponseEntity.status(404).build()
    }
}