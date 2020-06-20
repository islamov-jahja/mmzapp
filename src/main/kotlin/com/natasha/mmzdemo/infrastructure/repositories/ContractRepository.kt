package com.natasha.mmzdemo.infrastructure.repositories

import com.natasha.mmzdemo.domain.core.entity.Contract
import com.natasha.mmzdemo.domain.core.repositories.client.SpringClientRepository
import com.natasha.mmzdemo.domain.core.repositories.contract.IContractRepository
import com.natasha.mmzdemo.domain.core.repositories.contract.SpringContractRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ContractRepository(@Autowired private val springContactRepository: SpringContractRepository) : IContractRepository {
    override fun save(contract: Contract) {
        springContactRepository.save(contract)
    }

}