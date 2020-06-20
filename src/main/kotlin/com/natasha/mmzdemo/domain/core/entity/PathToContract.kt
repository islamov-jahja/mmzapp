package com.natasha.mmzdemo.domain.core.entity

import com.natasha.mmzdemo.application.controllers.contract.dto.PathToContractDTO
import javax.persistence.*

@Entity(name = "path_to_contract")
class PathToContract(_path: String) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0

    @Column(name = "path")
    val path: String = _path

    fun toDTO():PathToContractDTO = PathToContractDTO(path)
}