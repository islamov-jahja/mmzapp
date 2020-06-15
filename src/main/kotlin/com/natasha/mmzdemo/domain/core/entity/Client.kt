package com.natasha.mmzdemo.domain.core.entity

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

    @Column("address", nullable = false)
    val address = _address

    @Column(name = "bic_client_bank", nullable = false)
    val bicClientBank = _bicClientBank

    @Column("correspondent_account", nullable = false)
    val correspondentAccount = _correspondentAccount

    @Column("payment_account", nullable = false)
    val paymentAccount = _paymentAccount

    @Column("phone_client", nullable = false)
    val phoneClient = _phoneClient

    @Column("email_of_client", unique = true)
    val emailOfClient = _emailOfClient

    @Column("fio_director", nullable = false)
    val fioDirector = _fioDirector

    @Column("position_director", nullable = false)
    val positionDirector = _positionDirector

    @Column("fio_contact_person", nullable = false)
    val fioContactPerson = _fioContactPerson

    @Column("phone_contact_person", nullable = false)
    val phoneContactPerson = _phoneContactPerson

    @Column("email_contact_person", nullable = false)
    val emailContactPerson = _emailContactPerson

    @Column("password", nullable = false)
    val password = _password
}