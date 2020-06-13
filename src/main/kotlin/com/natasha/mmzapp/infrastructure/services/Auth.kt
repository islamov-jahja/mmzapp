package com.natasha.mmzapp.infrastructure.services

import com.natasha.mmzapp.application.controllers.auth.dto.Client
import com.natasha.mmzapp.domain.core.repositories.client.IClientRepository
import com.natasha.mmzapp.domain.core.repositories.client.SpringClientRepository
import com.natasha.mmzapp.domain.core.services.IAuth
import com.natasha.mmzapp.infrastructure.helpers.PasswordProcessor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class Auth(@Autowired private val clientRepository: IClientRepository,
           @Autowired private val passwordProcessor: PasswordProcessor) : IAuth {
    override fun reg(client: Client) {
        val passwordHash = passwordProcessor.getHashOfPassword(client.password)
        client.setPasswordHash(passwordHash)
        clientRepository.add(client.toEntity())
     }

    override fun login() {

    }
}