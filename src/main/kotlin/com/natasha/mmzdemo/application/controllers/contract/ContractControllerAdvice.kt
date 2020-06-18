package com.natasha.mmzdemo.application.controllers.contract

import com.natasha.mmzdemo.application.controllers.contract.exceptions.WrongClientException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ContractControllerAdvice {
    @ExceptionHandler(value = [WrongClientException::class])
    fun wrongClientException(): ResponseEntity<Any>{
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build()
    }
}