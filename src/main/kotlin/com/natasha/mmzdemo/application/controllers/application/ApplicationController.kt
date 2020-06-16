package com.natasha.mmzdemo.application.controllers.application

import com.natasha.mmzdemo.application.controllers.application.dto.ApplicationRequest
import com.natasha.mmzdemo.application.controllers.application.dto.ApplicationResponse
import com.natasha.mmzdemo.application.controllers.application.dto.Si
import com.natasha.mmzdemo.domain.core.services.ApplicationService
import com.natasha.mmzdemo.middleware.security.AuthenticatedUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import springfox.documentation.swagger2.annotations.EnableSwagger2

@RestController
@RequestMapping("/application")
@EnableSwagger2
class ApplicationController(@Autowired val applicationService: ApplicationService) {
    @Autowired
    private var authenticatedUser: AuthenticatedUser = AuthenticatedUser()

    @PostMapping("")
    fun createApplication(@RequestBody application: ApplicationRequest): ResponseEntity<Any>{
        val userName = authenticatedUser.getUserName()
        applicationService.createApplication(application, userName)
        return ResponseEntity.ok().build()
    }

    @GetMapping("/{id}/listSi")
    fun getListOfSiInApplication(@PathVariable id: Long): ResponseEntity<List<Si>>{
        val listOfSi = applicationService.getListOfSi(id)
        return ResponseEntity.ok().body(listOfSi)
    }

    @GetMapping("")
    fun getApplications(): ResponseEntity<List<ApplicationResponse>>{
        val applicationsResponse = applicationService.getList()
        return ResponseEntity.ok().body(applicationsResponse)
    }
}