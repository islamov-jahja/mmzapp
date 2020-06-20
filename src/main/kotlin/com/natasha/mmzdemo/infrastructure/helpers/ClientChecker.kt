package com.natasha.mmzdemo.infrastructure.helpers

import com.natasha.mmzdemo.application.controllers.contract.exceptions.WrongClientException
import com.natasha.mmzdemo.infrastructure.repositories.ApplicationRepository
import com.natasha.mmzdemo.middleware.security.AuthenticatedUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ClientChecker(@Autowired private val authenticatedUser: AuthenticatedUser,
                    @Autowired private val applicationRepository: ApplicationRepository) {
    fun throwExceptionIfWrongClient(applicationId: Long){
        val username = authenticatedUser.userName
        val application = applicationRepository.getById(applicationId)
        if (application.client.emailOfClient != username){
            throw WrongClientException()
        }
    }
}