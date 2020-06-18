package com.natasha.mmzdemo.infrastructure.services

import com.natasha.mmzdemo.application.controllers.application.dto.ApplicationRequest
import com.natasha.mmzdemo.application.controllers.application.dto.ApplicationResponse
import com.natasha.mmzdemo.application.controllers.application.dto.Si
import com.natasha.mmzdemo.application.controllers.application.exceptions.ApplicationNotFoundException
import com.natasha.mmzdemo.application.controllers.application.exceptions.ListSiNotForApplicationNotFound
import com.natasha.mmzdemo.domain.core.entity.Application
import com.natasha.mmzdemo.domain.core.enums.ApplicationStatus
import com.natasha.mmzdemo.domain.core.services.ApplicationService
import com.natasha.mmzdemo.infrastructure.helpers.ApplicationDocxGenerator
import com.natasha.mmzdemo.infrastructure.models.Role
import com.natasha.mmzdemo.infrastructure.repositories.ApplicationRepository
import com.natasha.mmzdemo.infrastructure.repositories.ClientRepository
import com.natasha.mmzdemo.middleware.security.AuthenticatedUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*

@Component
class ApplicationServiceImpl(@Autowired private val applicationRepository: ApplicationRepository,
                             @Autowired private val clientRepository: ClientRepository) : ApplicationService {

    @Autowired
    private val applicationDocxGenerator: ApplicationDocxGenerator? = null

    override fun createApplication(application: ApplicationRequest, userName: String) {
        val client = clientRepository.getByEmail(userName)
        val fileName = generateApplicationFile(application)

        val app = Application(Date(), client, ApplicationStatus.Created.toString(), fileName)

        for (si in application.listSi){
            val siEntity = com.natasha.mmzdemo.domain.core.entity.Si(si.name, si.description, si.type, si.factoryNumber, si.count, si.numberOnRegister, si.note)
            app.addSi(siEntity)
        }

        applicationRepository.save(app)
    }

    override fun reorganize(id: Long, application: ApplicationRequest) {
        val applicationEntity = applicationRepository.getById(id)
        applicationEntity.clearList()

        val fileName = generateApplicationFile(application)
        applicationEntity.setFileName(fileName)

        for(si in application.listSi){
            val siEntity = com.natasha.mmzdemo.domain.core.entity.Si(si.name, si.description, si.type, si.factoryNumber, si.count, si.numberOnRegister, si.note)
            applicationEntity.addSi(siEntity)
        }

        applicationRepository.save(applicationEntity)
    }

    fun generateApplicationFile(application: ApplicationRequest): String{
        val fileName = UUID.randomUUID().toString() + ".docx"
        applicationDocxGenerator?.generate(application.listSi, Date(), fileName)
        return fileName
    }

    override fun getListOfSi(applicationId: Long): List<Si> {
        val listSi: MutableList<Si> = mutableListOf()

        try {
            val application = applicationRepository.getById(applicationId)

            val listSiWithEntities = application.getListSi()
            for (si in listSiWithEntities) {
                listSi.add(si.toDTO())
            }
        }catch (e: Exception){
            throw ListSiNotForApplicationNotFound()
        }

        return listSi
    }

    override fun getList(authenticatedUser: AuthenticatedUser): List<ApplicationResponse> {
        val listApplication: MutableList<ApplicationResponse> = mutableListOf()
        try {
            val listApplicationEntities = when(authenticatedUser.role){
                Role.Admin -> applicationRepository.getList()
                else -> {
                    val client = clientRepository.getByEmail(authenticatedUser.userName)
                    applicationRepository.getByClient(client)
                }
            }

            for(application in listApplicationEntities){
                listApplication.add(application.toApplicationResponse())
            }
        }catch (e: Exception){
            throw ApplicationNotFoundException()
        }

        return listApplication
    }
}