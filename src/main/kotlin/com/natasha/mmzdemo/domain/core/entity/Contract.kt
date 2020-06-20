package com.natasha.mmzdemo.domain.core.entity

import com.natasha.mmzdemo.application.controllers.contract.dto.ContractResponse
import com.natasha.mmzdemo.application.controllers.contract.dto.PathToContractDTO
import com.natasha.mmzdemo.domain.core.enums.ContractStatus
import java.util.*
import javax.persistence.*

@Entity(name = "contract")
class Contract(_status: ContractStatus, _mainFileName: String) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0

    @Column(name = "status")
    val status: String = _status.toString()

    @Column(name = "main_file_name")
    val mainFileName: String = _mainFileName

    @OneToMany(fetch = FetchType.EAGER, cascade = arrayOf(CascadeType.ALL))
    private val listPath: MutableList<PathToContract> = mutableListOf()

    @Column(name = "date_of_conclusion")
    private var dateOfConclusion: Date = Date()

    fun updateDate(){
        dateOfConclusion = Date()
    }

    fun addPath(pathToContract: PathToContract){
        listPath.add(pathToContract)
    }

    fun getListPath(): List<PathToContract>{
        return listPath.toList()
    }

    fun toDTO(): ContractResponse{
        val listPathDTO: MutableList<PathToContractDTO> = mutableListOf()
        for (path in getListPath()){
            listPathDTO.add(path.toDTO())
        }

        return ContractResponse(status, mainFileName, dateOfConclusion, listPathDTO)
    }
}