package com.natasha.mmzdemo.infrastructure.services

import com.natasha.mmzdemo.application.controllers.application.exceptions.InvalidApplicationStatus
import com.natasha.mmzdemo.application.controllers.contract.exceptions.ContractNotFoundException
import com.natasha.mmzdemo.application.controllers.contract.exceptions.WrongClientException
import com.natasha.mmzdemo.application.controllers.muster.dto.SiWithPriceDTO
import com.natasha.mmzdemo.domain.core.entity.Si
import com.natasha.mmzdemo.domain.core.enums.ApplicationStatus
import com.natasha.mmzdemo.domain.core.services.MusterService
import com.natasha.mmzdemo.infrastructure.helpers.CertificateOfCompletionDocxGenerator
import com.natasha.mmzdemo.infrastructure.helpers.InvoiceForPaymentDocxGenerator
import com.natasha.mmzdemo.infrastructure.models.Role
import com.natasha.mmzdemo.infrastructure.repositories.ApplicationRepository
import com.natasha.mmzdemo.infrastructure.repositories.ContractRepository
import com.natasha.mmzdemo.middleware.security.AuthenticatedUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*

@Component
class MusterServiceImpl(@Autowired private val authenticatedUser: AuthenticatedUser,
                        @Autowired private val applicationRepository: ApplicationRepository,
                        @Autowired private val contractRepository: ContractRepository) : MusterService {
    @Autowired
    private val invoiceForPaymentDocxGenerator: InvoiceForPaymentDocxGenerator? = null
    @Autowired val certificateOfCompletionDocxGenerator: CertificateOfCompletionDocxGenerator? = null

    override fun doMuster(listSiWithPrice: List<SiWithPriceDTO>, applicationId: Long) {
        val application = applicationRepository.getById(applicationId)
        if (application.getStatus() != ApplicationStatus.ContractCompleted){
            throw InvalidApplicationStatus()
        }

        if (authenticatedUser.role != Role.Admin){
            throw WrongClientException()
        }

        setPriceToSi(listSiWithPrice, application.getListSi())
        val invoiceFileName = UUID.randomUUID().toString() + ".docx"
        val certificateOfCompletionFileName = UUID.randomUUID().toString() + ".docx"

        certificateOfCompletionDocxGenerator?.generate(application.client, application.getListSi(), certificateOfCompletionFileName)
        invoiceForPaymentDocxGenerator?.generate(application.client, application.getListSi(), invoiceFileName)

        val contract = application.getContract() ?: throw ContractNotFoundException()

        application.setStatus(ApplicationStatus.Attorney)
        contract.invoiceFileName = invoiceFileName
        contract.certificateOfCompletionFileName = certificateOfCompletionFileName

        contractRepository.save(contract)
        applicationRepository.save(application)
    }

    fun setPriceToSi(listSiWithPrice: List<SiWithPriceDTO>, listSi: List<Si>){
        for (si in listSiWithPrice){
            val siEntity = listSi.find { it.id == si.siId }
            if (siEntity != null){
                siEntity._price = si.price
            }
        }
    }
}