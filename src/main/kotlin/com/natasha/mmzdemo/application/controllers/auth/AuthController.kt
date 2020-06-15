package com.natasha.mmzdemo.application.controllers.auth

import com.natasha.mmzdemo.application.controllers.auth.dto.Client
import com.natasha.mmzdemo.application.controllers.auth.dto.JWTRequest
import com.natasha.mmzdemo.application.controllers.auth.dto.JWTResponse
import com.natasha.mmzdemo.infrastructure.helpers.JwtTokenUtil
import com.natasha.mmzdemo.infrastructure.services.AuthServiceImpl
import com.natasha.mmzdemo.infrastructure.services.JwtUserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.RequestBody
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("api/auth")
@Component
class AuthController (@Autowired val auth: AuthServiceImpl,
                      @Autowired val userDetailsService: JwtUserDetailsService,
                      @Autowired val jwtTokenUtil: JwtTokenUtil,
                      @Autowired val authenticationManager: AuthenticationManager){

    @Path("/reg")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    fun reg(@RequestBody client: Client): Response{
        println(client)
        auth.reg(client)
        return Response.ok().build()
    }

    @Path("/login")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    fun login(@RequestBody authenticationRequest: JWTRequest): Response{
        try {
            authenticate(authenticationRequest.username, authenticationRequest.password);
        }catch (e: Exception){
            return Response.status(Response.Status.BAD_REQUEST).build()
        }

        val userDetails = userDetailsService.loadUserByUsername(authenticationRequest.username)
        val token = jwtTokenUtil.generateToken(userDetails);
        val jsonResponse: JWTResponse = JWTResponse(token.toString())
        return Response.ok(jsonResponse).build()
    }

    private fun authenticate(username: String, password: String) {
        try {
            authenticationManager.authenticate(UsernamePasswordAuthenticationToken(username, password));
        } catch (e: DisabledException) {
            throw Exception("USER_DISABLED", e);
        } catch (e: BadCredentialsException) {
            throw Exception("INVALID_CREDENTIALS", e);
        }
    }
}