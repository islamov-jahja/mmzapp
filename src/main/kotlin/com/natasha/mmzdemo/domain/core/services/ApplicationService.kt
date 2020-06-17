package com.natasha.mmzdemo.domain.core.services

import com.natasha.mmzdemo.application.controllers.application.dto.ApplicationRequest
import com.natasha.mmzdemo.application.controllers.application.dto.ApplicationResponse
import com.natasha.mmzdemo.application.controllers.application.dto.Si
import com.natasha.mmzdemo.middleware.security.AuthenticatedUser

interface ApplicationService {
    fun createApplication(application: ApplicationRequest, userName: String)
    fun getListOfSi(applicationId: Long): List<Si>
    fun getList(authenticatedUser: AuthenticatedUser): List<ApplicationResponse>
}