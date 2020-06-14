package com.natasha.mmzapp.config

import org.glassfish.jersey.server.ResourceConfig
import org.springframework.stereotype.Component

@Component
class JerseyConfig() : ResourceConfig() {
    init {
        packages("com.natasha.mmzapp.application.controllers")
    }
}