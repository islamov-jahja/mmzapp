package com.natasha.mmzdemo.application.controllers.contract

import com.natasha.mmzdemo.application.controllers.contract.dto.ContractResponse
import com.natasha.mmzdemo.domain.core.services.ContractService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import springfox.documentation.swagger2.annotations.EnableSwagger2

@EnableSwagger2
@CrossOrigin("*")
@RestController
@RequestMapping("/api/application/{id}/Contract")
class ContractController(@Autowired val contractService: ContractService) {
    @PostMapping("")
    fun createContract(@PathVariable id: Long): ResponseEntity<Any>{
        contractService.createContract(id)
        return ResponseEntity.ok().build()
    }

    @GetMapping("")
    fun getContract(@PathVariable id: Long): ResponseEntity<ContractResponse>{
        val contractResponse: ContractResponse = contractService.get(id)
        return ResponseEntity.ok(contractResponse)
    }


}