package com.natasha.mmzdemo.domain.core.entity

import javax.persistence.*

@Entity(name = "path_to_contract")
class PathToContract(_path: String) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "path_to_contract_id")
    val id: Long = 0

    @Column(name = "path")
    val path: String = _path
}