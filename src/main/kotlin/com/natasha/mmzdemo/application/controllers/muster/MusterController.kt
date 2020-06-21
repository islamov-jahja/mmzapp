package com.natasha.mmzdemo.application.controllers.muster

import com.natasha.mmzdemo.application.controllers.muster.dto.SiWithPriceDTO
import com.natasha.mmzdemo.domain.core.services.MusterService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import springfox.documentation.swagger2.annotations.EnableSwagger2

@EnableSwagger2
@CrossOrigin("*")
@RestController
@RequestMapping("/api/application/{id}/muster")
class MusterController(@Autowired private val musterService: MusterService) {
    @PostMapping("")
    fun toMuster(@PathVariable id: Long, @RequestBody listSiWithPrice: List<SiWithPriceDTO>): ResponseEntity<Any>{
        musterService.doMuster(listSiWithPrice, id)
        return ResponseEntity.ok().build()
    }
}