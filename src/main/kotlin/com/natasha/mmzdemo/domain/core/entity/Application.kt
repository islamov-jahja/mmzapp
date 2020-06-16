package com.natasha.mmzdemo.domain.core.entity

import com.natasha.mmzdemo.application.controllers.application.dto.ApplicationResponse
import com.natasha.mmzdemo.domain.core.enums.ApplicationStatus
import java.util.*
import javax.persistence.*

@Entity(name = "application")
class Application(_date: Date,
                  _client: Client,
                  _status: String,
                  _nameOfFile: String) {

    fun addSi(si: Si) {
        listSi.add(si)
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "application_id")
    val id: Long = 0

    @Column(name = "created_date")
    private val createdDate: Date = _date

    @OneToMany(fetch = FetchType.EAGER, cascade = arrayOf(CascadeType.ALL))
    private val listSi: MutableList<Si> = mutableListOf()

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private val client: Client = _client

    @Column(name = "status")
    private val status: String = _status

    @Column(name = "name_of_file")
    private val nameOfFile: String = _nameOfFile

    fun getListSi(): List<Si> {
        return listSi.toList()
    }

    fun toApplicationResponse(): ApplicationResponse{
        return ApplicationResponse(id, createdDate, nameOfFile)
    }
}