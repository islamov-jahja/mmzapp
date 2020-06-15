package com.natasha.mmzdemo.domain.core.repositories.client

import com.natasha.mmzdemo.domain.core.entity.Client

interface IClientRepository {
    fun add(client: Client)
    fun getByEmail(emailOfClient: String): Client?
}