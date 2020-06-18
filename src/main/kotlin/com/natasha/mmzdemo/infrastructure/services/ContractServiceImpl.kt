package com.natasha.mmzdemo.infrastructure.services

import com.natasha.mmzdemo.application.controllers.contract.exceptions.WrongClientException
import com.natasha.mmzdemo.domain.core.services.ContractService
import com.natasha.mmzdemo.infrastructure.helpers.ApplicationDocxGenerator
import com.natasha.mmzdemo.infrastructure.helpers.ContractDocxGenerator
import com.natasha.mmzdemo.infrastructure.repositories.ApplicationRepository
import com.natasha.mmzdemo.infrastructure.repositories.ClientRepository
import com.natasha.mmzdemo.middleware.security.AuthenticatedUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ContractServiceImpl(@Autowired private val authenticatedUser: AuthenticatedUser,
                          @Autowired private val applicationRepository: ApplicationRepository,
                          @Autowired private val clientRepository: ClientRepository) : ContractService {
    @Autowired
    private val contractDocxGenerator: ContractDocxGenerator? = null

    override fun createContract(applicationId: Long) {
        throwExceptionIfWrongClient(applicationId)
        val client = applicationRepository.getById(applicationId).client
        contractDocxGenerator?.generate(client, "f3.docx")
    }

    private fun throwExceptionIfWrongClient(applicationId: Long){
        val username = authenticatedUser.userName
        val application = applicationRepository.getById(applicationId)
        if (application.client.emailOfClient != username){
            throw WrongClientException()
        }
    }
}