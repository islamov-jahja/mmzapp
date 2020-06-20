package com.natasha.mmzdemo.domain.core.repositories.contract

import com.natasha.mmzdemo.domain.core.entity.Contract

interface IContractRepository {
    fun save(contract: Contract)
}