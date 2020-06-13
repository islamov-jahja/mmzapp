package com.natasha.mmzapp.application.controllers

import org.glassfish.jersey.server.ResourceConfig
import org.springframework.stereotype.Component

@Component
class JerseyConfig() : ResourceConfig() {
    init {
        packages(JerseyConfig::class.java.`package`.name)
    }
}