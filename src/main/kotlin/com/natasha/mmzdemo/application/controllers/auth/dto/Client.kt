package com.natasha.mmzdemo.application.controllers.auth.dto

import com.natasha.mmzdemo.domain.core.entity.Client

data class Client (val inn: String,
                   val name: String,
                   val kpp: String,
                   val address: String,
                   val bicClientBank: String,
                   val correspondentAccount: String,
                   val paymentAccount: String,
                   val phoneClient: String,
                   val emailOfClient: String,
                   val password: String,
                   val fioDirector: String,
                   val positionDirector: String,
                   val contactPerson: ContactPerson){
    private var passwordHash = ""

    fun toEntity(): Client {
        return Client(
                inn,
                name,
                kpp,
                address,
                bicClientBank,
                correspondentAccount,
                paymentAccount,
                phoneClient,
                emailOfClient,
                passwordHash,
                fioDirector,
                positionDirector,
                contactPerson.fio,
                contactPerson.phone,
                contactPerson.email
        )
    }

    fun setPasswordHash(_passwordHash: String){
        passwordHash = _passwordHash
    }
}