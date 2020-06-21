package com.natasha.mmzdemo.application.controllers.application

import com.natasha.mmzdemo.application.controllers.application.dto.*
import com.natasha.mmzdemo.domain.core.services.ApplicationService
import com.natasha.mmzdemo.middleware.security.AuthenticatedUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.RequestEntity
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.util.*

@CrossOrigin(origins = arrayOf("*"))
@RestController
@RequestMapping("/api/application")
@EnableSwagger2
class ApplicationController(@Autowired val applicationService: ApplicationService) {
    @Autowired
    private var authenticatedUser: AuthenticatedUser = AuthenticatedUser()

    @PostMapping("")
    fun createApplication(@RequestBody application: ApplicationRequest): ResponseEntity<Any>{
        val userName = authenticatedUser.userName
        applicationService.createApplication(application, userName)
        return ResponseEntity.ok().build()
    }

    @PostMapping("/{id}/confirmPayWithClient")
    fun confirmPayWithClient(@PathVariable id: Long): ResponseEntity<Any>{
        applicationService.confirmPaidWithClient(id)
        return ResponseEntity.ok().build()
    }

    @PostMapping("/{id}/confirmPaidWithAdmin")
    fun confirmPaidWithAdmin(@PathVariable id: Long): ResponseEntity<Any>{
        applicationService.confirmPaidWithAdmin(id)
        return ResponseEntity.ok().build()
    }

    @GetMapping("/{id}/listSi")
    fun getListOfSiInApplication(@PathVariable id: Long): ResponseEntity<List<Si>>{
        val listOfSi = applicationService.getListOfSi(id)
        return ResponseEntity.ok().body(listOfSi)
    }

    @PostMapping("/{id}/reorganize")
    fun reorganize(@PathVariable id: Long, @RequestBody application: ApplicationRequest): ResponseEntity<Any>{
        applicationService.reorganize(id, application)
        return ResponseEntity.ok().build()
    }

    @GetMapping("")
    fun getApplications(): ResponseEntity<List<ApplicationResponse>>{
        val applicationsResponse = applicationService.getList(authenticatedUser)
        return ResponseEntity.ok().body(applicationsResponse)
    }

    @GetMapping("/{id}")
    fun getApplication(@PathVariable id: Long): ResponseEntity<ApplicationResponse>{
        val application = applicationService.getApplicationById(id)
        return ResponseEntity.ok(application)
    }

    @PostMapping("/{id}/deny")
    fun denyApplication(@PathVariable id: Long, @RequestBody denyMessage: DenyRequest): ResponseEntity<Any>{
        applicationService.denyApplication(DeniedMessage(denyMessage.denyMessage, Date()), id)
        return ResponseEntity.ok().build()
    }
}