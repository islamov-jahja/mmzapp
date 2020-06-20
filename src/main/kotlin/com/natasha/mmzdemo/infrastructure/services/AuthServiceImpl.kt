package com.natasha.mmzdemo.infrastructure.services

import com.natasha.mmzdemo.application.controllers.auth.dto.Client
import com.natasha.mmzdemo.application.controllers.auth.exceptions.ClientNotFoundException
import com.natasha.mmzdemo.domain.core.repositories.client.IClientRepository
import com.natasha.mmzdemo.domain.core.services.AuthService
import com.natasha.mmzdemo.infrastructure.helpers.PasswordProcessor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class AuthServiceImpl(@Autowired private val clientRepository: IClientRepository,
                      @Autowired private val passwordProcessor: PasswordProcessor) : AuthService {
    override fun reg(client: Client) {
        val passwordHash = passwordProcessor.getHashOfPassword(client.password)
        val entity = client.toEntity()
        entity.setPasswordHash(passwordHash)
        clientRepository.add(entity)
     }

    override fun getClient(userName: String): Client {
        val client = clientRepository.getByEmail(userName) ?: throw ClientNotFoundException()

        return client.toDTO()
    }

    override fun getClient(id: Long): Client {
        val client = clientRepository.getById(id) ?: throw ClientNotFoundException();
        return client.toDTO()
    }
}