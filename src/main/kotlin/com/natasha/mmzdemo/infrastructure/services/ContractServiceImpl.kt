package com.natasha.mmzdemo.infrastructure.services

import com.natasha.mmzdemo.application.controllers.application.exceptions.InvalidApplicationStatus
import com.natasha.mmzdemo.application.controllers.contract.dto.ContractResponse
import com.natasha.mmzdemo.application.controllers.contract.exceptions.ContractNotFoundException
import com.natasha.mmzdemo.domain.core.entity.Contract
import com.natasha.mmzdemo.domain.core.entity.PathToContract
import com.natasha.mmzdemo.domain.core.enums.ApplicationStatus
import com.natasha.mmzdemo.domain.core.enums.ContractStatus
import com.natasha.mmzdemo.domain.core.services.ContractService
import com.natasha.mmzdemo.infrastructure.helpers.ClientChecker
import com.natasha.mmzdemo.infrastructure.helpers.ContractDocxGenerator
import com.natasha.mmzdemo.infrastructure.models.Role
import com.natasha.mmzdemo.infrastructure.repositories.ApplicationRepository
import com.natasha.mmzdemo.infrastructure.repositories.ContractRepository
import com.natasha.mmzdemo.middleware.security.AuthenticatedUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*

@Component
class ContractServiceImpl(@Autowired private val authenticatedUser: AuthenticatedUser,
                          @Autowired private val applicationRepository: ApplicationRepository,
                          @Autowired private val clientChecker: ClientChecker,
                          @Autowired private val contractRepository: ContractRepository) : ContractService {
    @Autowired
    private val contractDocxGenerator: ContractDocxGenerator? = null

    override fun createContract(applicationId: Long) {
        val application = applicationRepository.getById(applicationId)
        if (application.getStatus() != ApplicationStatus.Created){
            throw InvalidApplicationStatus()
        }

        val client = application.client
        val fileName = UUID.randomUUID().toString() + ".docx"

        val contract = Contract(ContractStatus.ReorganizeByAdmin, fileName)
        contract.addPath(PathToContract(fileName))
        contractRepository.save(contract)
        application.setContract(contract)
        contractDocxGenerator?.generate(client, fileName)
        application.setStatus(ApplicationStatus.Contract)
        applicationRepository.save(application)
    }

    override fun get(applicationId: Long): ContractResponse {
        if (authenticatedUser.role == Role.Client){
            clientChecker.throwExceptionIfWrongClient(applicationId)
        }

        val application = applicationRepository.getById(applicationId)
        if (application.getStatus() < ApplicationStatus.Contract){
            throw InvalidApplicationStatus()
        }

        val contract = application.getContract()?: throw ContractNotFoundException()
        return contract.toDTO()
    }
}