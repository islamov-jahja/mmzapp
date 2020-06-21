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
         _note: String,
        _price: Double?) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0

    @Column(name = "name", nullable = false)
    val name = _name

    @Column(name = "description")
    val description = _description

    @Column(name = "type")
    val type: String = _type

    @Column(name = "factory_number")
    val factoryNumber: String = _factoryNumber

    @NotNull
    @Column(name = "count", nullable = false)
    val count: Int = _count

    @Column(name = "number_on_register")
    val numberOnRegister: String = _numberOnRegister

    @Column(name = "note")
    val note: String = _note

    @Column(name = "price")
    var _price: Double? = _price

    fun toDTO(): Si{
        return Si(id, name, description, type, factoryNumber, count, numberOnRegister, note)
    }
}