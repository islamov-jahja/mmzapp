package com.natasha.mmzdemo.domain.core.services

import com.natasha.mmzdemo.application.controllers.contract.dto.ContractResponse
import org.springframework.web.multipart.MultipartFile

interface ContractService {
    fun createContract(applicationId: Long)
    fun get(applicationId: Long): ContractResponse
    fun uploadContract(applicationId: Long, file: MultipartFile)
    fun confirmContract(applicationId: Long)
}