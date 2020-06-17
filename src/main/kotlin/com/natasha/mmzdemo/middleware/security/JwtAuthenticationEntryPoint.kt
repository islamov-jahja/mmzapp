package com.natasha.mmzdemo.middleware.security

import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtAuthenticationEntryPoint : AuthenticationEntryPoint {
    override fun commence(request: HttpServletRequest?, response: HttpServletResponse?, authException: AuthenticationException?) {
        if (response != null) {
            response.addHeader("Access-Control-Allow-Header", "X-Requested-With, Access-Control-Allow-Headers, Access-Control-Request-Method, Content-Type, Accept, Origin, Authorization")
        }
        if (response != null) {
            response.addHeader("Access-Control-Max-Age", "3600")
        }
        if (response != null) {
            response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, PATCH, DELETE, OPTIONS")
        }
        if (response != null) {
            response.addHeader("Access-Control-Allow-Origin", "*")
        }

        if (response != null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized")
        };
    }
}