package com.natasha.mmzdemo.application.controllers.application.dto

import java.util.*

data class ApplicationResponse(val id: Long,
                               val createdDate: Date,
                               val nameOfFile: String) {
}