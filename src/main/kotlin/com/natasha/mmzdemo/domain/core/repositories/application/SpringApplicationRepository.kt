package com.natasha.mmzdemo.domain.core.repositories.application

import com.natasha.mmzdemo.domain.core.entity.Application
import com.natasha.mmzdemo.domain.core.entity.Client
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Component

interface SpringApplicationRepository : CrudRepository<Application, Long> {
    fun getByClient(client: Client): MutableIterable<Application>
}