package com.natasha.mmzdemo.config

import com.natasha.mmzdemo.application.controllers.auth.AuthController
import org.glassfish.jersey.server.ResourceConfig
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider
import org.springframework.core.type.filter.AnnotationTypeFilter
import org.springframework.stereotype.Component
import org.springframework.util.ClassUtils
import java.util.stream.Collectors
import javax.ws.rs.Path
import javax.ws.rs.ext.Provider


@Component
class JerseyConfig() : ResourceConfig() {
    init {
        register(AuthController::class.java)
        //packages("com.natasha.mmzdemo.application.controllers")
    }
}