package com.natasha.mmzdemo.domain.core.repositories.application

import com.natasha.mmzdemo.domain.core.entity.Application

interface IApplicationRepository {
    fun create(application: Application)
    fun getById(applicationId: Long): Application
    fun getList(): MutableIterable<Application>
}