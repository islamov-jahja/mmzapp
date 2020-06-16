package com.natasha.mmzdemo.infrastructure.repositories

import com.natasha.mmzdemo.domain.core.entity.Application
import com.natasha.mmzdemo.domain.core.entity.Client
import com.natasha.mmzdemo.domain.core.repositories.application.IApplicationRepository
import com.natasha.mmzdemo.domain.core.repositories.application.SpringApplicationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*

@Component
class ApplicationRepository(@Autowired private val springApplicationRepository: SpringApplicationRepository ) : IApplicationRepository{
    override fun create(application: Application) {
        springApplicationRepository.save(application)
    }

    override fun getById(applicationId: Long): Application {
        return springApplicationRepository.findById(applicationId).get()
    }

    override fun getList(): MutableIterable<Application> {
        return springApplicationRepository.findAll()
    }

    override fun getByClient(client: Client): MutableIterable<Application> {
        return springApplicationRepository.getByClient(client)
    }
}