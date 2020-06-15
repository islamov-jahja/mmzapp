package com.natasha.mmzdemo.domain.core.services

import com.natasha.mmzdemo.application.controllers.auth.dto.Client

interface AuthService {
    fun reg(client: Client)
}