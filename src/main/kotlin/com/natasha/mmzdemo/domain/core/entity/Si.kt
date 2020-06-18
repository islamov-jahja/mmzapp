package com.natasha.mmzdemo.domain.core.entity

import com.natasha.mmzdemo.application.controllers.application.dto.Si
import org.jetbrains.annotations.NotNull
import javax.persistence.*

@Entity(name = "si")
class Si(_name: String,
         _description: String,
         _type: String,
         _factoryNumber: String,
         _count: Int,
         _numberOnRegister: String,
         _note: String) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "si_id")
    private val id: Long = 0

    @Column(name = "name", nullable = false)
    private val name = _name

    @Column(name = "description")
    private val description = _description

    @Column(name = "type")
    private val type: String = _type

    @Column(name = "factory_number")
    private val factoryNumber: String = _factoryNumber

    @NotNull
    @Column(name = "count", nullable = false)
    private val count: Int = _count

    @Column(name = "number_on_register")
    private val numberOnRegister: String = _numberOnRegister

    @Column(name = "note")
    private val note: String = _note

    fun toDTO(): Si{
        return Si(name, description, type, factoryNumber, count, numberOnRegister, note)
    }
}