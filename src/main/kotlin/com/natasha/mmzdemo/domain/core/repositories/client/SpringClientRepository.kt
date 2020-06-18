package com.natasha.mmzdemo.domain.core.repositories.client

import com.natasha.mmzdemo.domain.core.entity.Client
import org.springframework.data.repository.CrudRepository

interface SpringClientRepository : CrudRepository<Client, Long> {
    fun getByEmailOfClient(emailOfClient: String): Client
    fun getById(id: Long): Client
}