package com.natasha.mmzdemo.application.controllers.application.dto

import com.natasha.mmzdemo.domain.core.enums.ApplicationStatus
import java.util.*

data class ApplicationResponse(val id: Long,
                               val createdDate: Date,
                               val nameOfFile: String,
                               val status: ApplicationStatus) {
}