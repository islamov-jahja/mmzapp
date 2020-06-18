package com.natasha.mmzdemo.domain.core.entity

import com.natasha.mmzdemo.application.controllers.application.dto.DeniedMessage
import java.util.*
import javax.persistence.*

@Entity(name = "denied_message")
class DeniedMessage(_date: Date, _message: String) {
    fun toDTO(): DeniedMessage? {
        return DeniedMessage(message, date)
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0

    @Column(name = "date")
    private val date: Date = _date

    @Column(name = "message")
    private val message: String = _message

    @OneToOne(mappedBy = "deniedMessage")
    private val application: Application? = null
}