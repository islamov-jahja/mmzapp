package com.natasha.mmzapp.domain.core.repositories.client

import com.natasha.mmzapp.domain.core.entity.Client
import org.springframework.data.repository.CrudRepository

interface SpringClientRepository : CrudRepository<Client, Long> {
    fun getByEmailOfClient(emailOfClient: String): Client
}