package com.natasha.mmzdemo.application.controllers.application.dto

import com.natasha.mmzdemo.domain.core.entity.Application

data class Si(val name: String,
              val description: String,
              val type: String,
              val factoryNumber: String,
              val count: Int,
              val numberOnRegister: String,
              val note: String) {
}