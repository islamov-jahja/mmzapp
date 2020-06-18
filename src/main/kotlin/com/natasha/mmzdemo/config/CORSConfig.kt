package com.natasha.mmzdemo.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
@EnableWebMvc
class CORSConfig : WebMvcConfigurer {
    @Override
    override fun addCorsMappings(registry: CorsRegistry) {
        println("cors")
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
    }
}