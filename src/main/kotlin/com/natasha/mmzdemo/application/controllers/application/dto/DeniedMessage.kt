package com.natasha.mmzdemo.application.controllers.application.dto

import com.natasha.mmzdemo.domain.core.entity.DeniedMessage
import java.util.*

data class DeniedMessage(val message: String, val date: Date) {
    fun toEntity(): DeniedMessage{
        return DeniedMessage(date, message)
    }
}