package com.natasha.mmzdemo.application.controllers.auth

import com.natasha.mmzdemo.application.controllers.auth.dto.Client
import com.natasha.mmzdemo.application.controllers.auth.dto.JWTRequest
import com.natasha.mmzdemo.application.controllers.auth.dto.JWTResponse
import com.natasha.mmzdemo.application.controllers.auth.exceptions.ClientExistsException
import com.natasha.mmzdemo.infrastructure.helpers.JwtTokenUtil
import com.natasha.mmzdemo.infrastructure.models.Role
import com.natasha.mmzdemo.infrastructure.services.AuthServiceImpl
import com.natasha.mmzdemo.infrastructure.services.JwtUserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/auth")
class AuthController (@Autowired val auth: AuthServiceImpl,
                      @Autowired val userDetailsService: JwtUserDetailsService,
                      @Autowired val jwtTokenUtil: JwtTokenUtil,
                      @Autowired val authenticationManager: AuthenticationManager){

    @GetMapping("/nds")
    fun ndsNotWant(): ResponseEntity<String>{
        return ResponseEntity.status(200).body("Наташа, я не люблю считать НДС((")
    }

   @PostMapping("/reg")
    fun reg(@RequestBody client: Client): ResponseEntity<Any>{

       try {
           auth.reg(client)
       }catch (e: Exception){
           throw ClientExistsException()
       }
        return ResponseEntity.status(200).build()
    }

    @PostMapping("/login")
    fun login(@RequestBody authenticationRequest: JWTRequest): ResponseEntity<Any>{
        try {
            authenticate(authenticationRequest.username, authenticationRequest.password);
        }catch (e: Exception){
            return ResponseEntity.status(400).build()
        }

        val userDetails = userDetailsService.loadUserByUsername(authenticationRequest.username)
        val token = jwtTokenUtil.generateToken(userDetails)
        val jsonResponse: JWTResponse = JWTResponse(token.toString(), getROle(userDetails))
        println("f")
        return ResponseEntity.ok(jsonResponse)
    }

    private fun getROle(userDetails: UserDetails): Role{
        return when(val isAdmin = userDetails.authorities.contains(SimpleGrantedAuthority(Role.Admin.toString()))){
            true -> Role.Admin
            else -> Role.Client
        }
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