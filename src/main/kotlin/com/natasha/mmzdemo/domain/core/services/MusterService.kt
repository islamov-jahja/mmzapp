package com.natasha.mmzdemo.domain.core.services

import com.natasha.mmzdemo.application.controllers.muster.dto.SiWithPriceDTO

interface MusterService {
    fun doMuster(listSiWithPrice: List<SiWithPriceDTO>, applicationId: Long)
}