package com.natasha.mmzdemo.domain.core.services

import com.natasha.mmzdemo.application.controllers.contract.dto.ContractResponse

interface ContractService {
    fun createContract(applicationId: Long)
    fun get(applicationId: Long): ContractResponse
}