package com.natasha.mmzdemo.domain.core.entity

import com.natasha.mmzdemo.application.controllers.auth.dto.Client
import com.natasha.mmzdemo.application.controllers.auth.dto.ContactPerson
import javax.persistence.*

@Entity(name = "client")
class Client(_inn: String,
             _name: String,
             _kpp: String,
             _address: String,
             _bicClientBank: String,
             _correspondentAccount: String,
             _paymentAccount: String,
             _phoneClient: String,
             _emailOfClient: String,
             _password:String,
             _fioDirector: String,
             _positionDirector: String,
             _fioContactPerson: String,
             _phoneContactPerson: String,
             _emailContactPerson: String) {

    fun toDTO(): Client {
        val contactPerson = ContactPerson(fioContactPerson, phoneContactPerson, emailContactPerson)
        return Client(inn, name, kpp, address, bicClientBank, correspondentAccount, paymentAccount, phoneClient, emailOfClient, "", fioDirector, positionDirector, contactPerson)
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "client_id")
    val id: Long = 0

    @Column(name = "inn", nullable = false)
    val inn = _inn

    @Column(name = "name", nullable = false)
    val name = _name

    @Column(name = "kpp", nullable = false)
    val kpp = _kpp

    @Column(name = "address", nullable = false)
    val address = _address

    @Column(name = "bic_client_bank", nullable = false)
    val bicClientBank = _bicClientBank

    @Column(name = "correspondent_account", nullable = false)
    val correspondentAccount = _correspondentAccount

    @Column(name = "payment_account", nullable = false)
    val paymentAccount = _paymentAccount

    @Column(name = "phone_client", nullable = false)
    val phoneClient = _phoneClient

    @Column(name = "email_of_client", unique = true)
    val emailOfClient = _emailOfClient

    @Column(name = "fio_director", nullable = false)
    val fioDirector = _fioDirector

    @Column(name = "position_director", nullable = false)
    val positionDirector = _positionDirector

    @Column(name = "fio_contact_person", nullable = false)
    val fioContactPerson = _fioContactPerson

    @Column(name = "phone_contact_person", nullable = false)
    val phoneContactPerson = _phoneContactPerson

    @Column(name = "email_contact_person", nullable = false)
    val emailContactPerson = _emailContactPerson

    @Column(name = "password", nullable = false)
    val password = _password
}