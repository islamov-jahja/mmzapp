package com.natasha.mmzdemo.application.controllers.contract

import com.natasha.mmzdemo.application.controllers.contract.exceptions.InvalidContractStatusException
import com.natasha.mmzdemo.application.controllers.contract.exceptions.WrongClientException
import com.natasha.mmzdemo.application.controllers.dto.ErrorResponse
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

    @ExceptionHandler(value = [InvalidContractStatusException::class])
    fun invalidContractStatus(): ResponseEntity<Any>{
        val error = ErrorResponse(3, "запрашиваемая операция недоступна с контрактом в данном его статусе")
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error)
    }
}