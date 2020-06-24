package com.natasha.mmzdemo.domain.core.entity

import com.natasha.mmzdemo.application.controllers.application.dto.ApplicationResponse
import com.natasha.mmzdemo.domain.core.enums.ApplicationStatus
import org.jetbrains.annotations.Nullable
import java.util.*
import javax.persistence.*

@Entity(name = "application")
class Application(_date: Date,
                  _client: Client,
                  _status: ApplicationStatus,
                  _nameOfFile: String,
                  _deniedMessage: DeniedMessage?) {

    fun addSi(si: Si) {
        listSi.add(si)
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0

    @Column(name = "created_date")
    private val createdDate: Date = _date

    @OneToMany(fetch = FetchType.EAGER, cascade = arrayOf(CascadeType.ALL))
    private val listSi: MutableList<Si> = mutableListOf()

    @OneToMany
    private var contracts: MutableList<Contract> = mutableListOf()

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    val client: Client = _client

    @Column(name = "status")
    private var status: String = _status.toString()

    @Column(name = "name_of_file")
    private var nameOfFile: String = _nameOfFile

    @OneToOne(cascade = arrayOf(CascadeType.ALL))
    @JoinColumn(name= "denied_message_id")
    @Nullable
    var deniedMessage: DeniedMessage? = _deniedMessage

    fun setContract(_contract: Contract){
        contracts.clear()
        contracts.add(_contract)
    }

    fun getContract(): Contract?{
        return contracts[0]
    }

    fun getListSi(): List<Si> {
        return listSi.toList()
    }

    fun setFileName(_fileName: String){
        nameOfFile = _fileName
    }

    fun clearList(){
        listSi.clear()
    }

    fun toApplicationResponse(): ApplicationResponse{
        return ApplicationResponse(id, client.id.toLong(), createdDate, "https://mmnewapp.herokuapp.com/$nameOfFile", deniedMessage?.toDTO(), getStatus())
    }

    fun setStatus(_status: ApplicationStatus) {
        status = _status.toString()
    }

    fun getStatus(): ApplicationStatus{
        return when(status){
            ApplicationStatus.Created.toString() -> ApplicationStatus.Created
            ApplicationStatus.Contract.toString() -> ApplicationStatus.Contract
            ApplicationStatus.Closed.toString() -> ApplicationStatus.Closed
            ApplicationStatus.Denied.toString() -> ApplicationStatus.Denied
            ApplicationStatus.ContractCompleted.toString() -> ApplicationStatus.ContractCompleted
            ApplicationStatus.Attorney.toString() -> ApplicationStatus.Attorney
            ApplicationStatus.TryingPaid.toString() -> ApplicationStatus.TryingPaid
            ApplicationStatus.SuccessfulPaid.toString() -> ApplicationStatus.SuccessfulPaid
            ApplicationStatus.ResultSend.toString() -> ApplicationStatus.ResultSend
            ApplicationStatus.ResultObtained.toString() -> ApplicationStatus.ResultObtained
            else -> ApplicationStatus.Closed
        }
    }
}