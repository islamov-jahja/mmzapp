package com.natasha.mmzapp.infrastructure.repositories

import com.natasha.mmzapp.domain.core.entity.Client
import com.natasha.mmzapp.domain.core.repositories.client.IClientRepository
import com.natasha.mmzapp.domain.core.repositories.client.SpringClientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ClientRepository(@Autowired private val springClientRepository: SpringClientRepository): IClientRepository{
    override fun add(client: Client) {
        springClientRepository.save(client)
    }

    override fun getByEmail(emailOfClient: String): Client {
        return springClientRepository.getByEmailOfClient(emailOfClient)
    }
}