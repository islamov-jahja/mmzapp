package com.natasha.mmzdemo.domain.core.repositories.application

import com.natasha.mmzdemo.domain.core.entity.Application
import com.natasha.mmzdemo.domain.core.entity.Client

interface IApplicationRepository {
    fun save(application: Application)
    fun getById(applicationId: Long): Application
    fun getList(): MutableIterable<Application>
    fun getByClient(client: Client): MutableIterable<Application>
}