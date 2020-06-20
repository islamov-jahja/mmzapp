package com.natasha.mmzdemo.domain.core.repositories.contract

import com.natasha.mmzdemo.domain.core.entity.Contract
import org.springframework.data.repository.CrudRepository

interface SpringContractRepository : CrudRepository<Contract, Long> {
}