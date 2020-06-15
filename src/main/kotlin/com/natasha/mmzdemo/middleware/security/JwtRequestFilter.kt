package com.natasha.mmzdemo.middleware.security

import com.natasha.mmzdemo.infrastructure.helpers.JwtTokenUtil
import com.natasha.mmzdemo.infrastructure.services.JwtUserDetailsService
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.SignatureException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Component
class JwtRequestFilter : OncePerRequestFilter() {

    @Autowired
    private val jwtUserDetailsService: JwtUserDetailsService? = null


    @Autowired
    private val jwtTokenUtil: JwtTokenUtil? = null

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val requestTokenHeader = request.getHeader("Authorization")

        var username: String? = null
        var jwtToken: String = ""


        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7)
        }

        try {
            if (jwtTokenUtil != null) {
                username = jwtTokenUtil.getUsernameFromToken(jwtToken)
            };

        } catch (e: IllegalArgumentException) {
            println("Unable to get JWT Token")
        } catch (e: ExpiredJwtException) {
            println("JWT Token has expired")
        } catch (e: SignatureException) {
            println("bad")
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            val userDetails = jwtUserDetailsService!!.loadUserByUsername(username)

            if (jwtTokenUtil != null) {
                if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
                    val usernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.authorities)


                    usernamePasswordAuthenticationToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken)
                }
            }
        }

        filterChain.doFilter(request, response)
    }
}