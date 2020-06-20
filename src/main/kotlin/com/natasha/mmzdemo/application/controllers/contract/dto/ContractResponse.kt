package com.natasha.mmzdemo.application.controllers.contract.dto

import java.util.*

data class ContractResponse(val status: String,
                            val mainFileName: String,
                            val dateOfConclusion: Date?,
                            val listPath: List<PathToContractDTO>) {
}