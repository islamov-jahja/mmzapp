package com.natasha.mmzapp.domain.core.services

import com.natasha.mmzapp.application.controllers.auth.dto.Client
import com.natasha.mmzapp.domain.core.repositories.client.IClientRepository
import org.springframework.beans.factory.annotation.Autowired

interface IAuth {
    fun reg(client: Client)
    fun login()
}