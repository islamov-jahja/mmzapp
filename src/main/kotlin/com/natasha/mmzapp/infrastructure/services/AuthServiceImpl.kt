package com.natasha.mmzapp.infrastructure.services

import com.natasha.mmzapp.application.controllers.auth.dto.Client
import com.natasha.mmzapp.domain.core.repositories.client.IClientRepository
import com.natasha.mmzapp.domain.core.services.AuthService
import com.natasha.mmzapp.infrastructure.helpers.PasswordProcessor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class AuthServiceImpl(@Autowired private val clientRepository: IClientRepository,
                      @Autowired private val passwordProcessor: PasswordProcessor) : AuthService {
    override fun reg(client: Client) {
        val passwordHash = passwordProcessor.getHashOfPassword(client.password)
        client.setPasswordHash(passwordHash)
        clientRepository.add(client.toEntity())
     }
}