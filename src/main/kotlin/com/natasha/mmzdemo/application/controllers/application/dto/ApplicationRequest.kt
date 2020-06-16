package com.natasha.mmzdemo.application.controllers.application.dto

import com.natasha.mmzdemo.domain.core.entity.Application
import com.natasha.mmzdemo.domain.core.entity.Client
import com.natasha.mmzdemo.domain.core.enums.ApplicationStatus
import java.util.*

data class ApplicationRequest(val listSi: List<Si>) {
}