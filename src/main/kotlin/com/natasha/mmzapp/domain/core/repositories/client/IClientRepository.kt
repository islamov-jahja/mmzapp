package com.natasha.mmzapp.domain.core.repositories.client

import com.natasha.mmzapp.domain.core.entity.Client

interface IClientRepository {
    fun add(client: Client)
    fun getByEmail(emailOfClient: String): Client?
}