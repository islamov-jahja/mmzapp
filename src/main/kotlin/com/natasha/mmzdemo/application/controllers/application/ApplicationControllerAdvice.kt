package com.natasha.mmzdemo.application.controllers.application

import com.natasha.mmzdemo.application.controllers.application.exceptions.ApplicationNotFoundException
import com.natasha.mmzdemo.application.controllers.application.exceptions.InvalidApplicationStatus
import com.natasha.mmzdemo.application.controllers.application.exceptions.ListSiNotForApplicationNotFound
import com.natasha.mmzdemo.application.controllers.dto.ErrorResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ApplicationControllerAdvice {
    @ExceptionHandler(value = [ListSiNotForApplicationNotFound::class, ApplicationNotFoundException::class])
    fun notFoundException(): ResponseEntity<Any>{
        return ResponseEntity.status(404).build()
    }

    @ExceptionHandler(value = [InvalidApplicationStatus::class])
    fun invalidApplicationStatus() :ResponseEntity<ErrorResponse>{
        val error = ErrorResponse(2, "Стастус заявки не предполагает данную операцию")
        return ResponseEntity.status(400).body(error)
    }
}