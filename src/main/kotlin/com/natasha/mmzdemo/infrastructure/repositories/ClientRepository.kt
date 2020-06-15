package com.natasha.mmzdemo.infrastructure.repositories

import com.natasha.mmzdemo.domain.core.entity.Client
import com.natasha.mmzdemo.domain.core.repositories.client.IClientRepository
import com.natasha.mmzdemo.domain.core.repositories.client.SpringClientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ClientRepository(@Autowired private val springClientRepository: SpringClientRepository): IClientRepository {
    override fun add(client: Client) {
        springClientRepository.save(client)
    }

    override fun getByEmail(emailOfClient: String): Client {
        return springClientRepository.getByEmailOfClient(emailOfClient)
    }
}