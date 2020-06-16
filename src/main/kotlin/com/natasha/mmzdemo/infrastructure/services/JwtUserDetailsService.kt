package com.natasha.mmzdemo.infrastructure.services

import com.natasha.mmzdemo.infrastructure.helpers.PasswordProcessor
import com.natasha.mmzdemo.infrastructure.models.Role
import com.natasha.mmzdemo.infrastructure.models.UserDetailsImpl
import com.natasha.mmzdemo.infrastructure.repositories.ClientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component

@Component
class JwtUserDetailsService(@Autowired val clientRepository: ClientRepository) : UserDetailsService {
    private val adminPosition: String = "adminOfServer"

    override fun loadUserByUsername(username: String?): UserDetails {
        if (username == null){
            throw UsernameNotFoundException(username)
        }

        val client = username.let { clientRepository.getByEmail(it) }

        if (client.positionDirector == adminPosition){
            return UserDetailsImpl(client.emailOfClient, client.password, mutableListOf<GrantedAuthority>(SimpleGrantedAuthority(Role.Admin.toString())))
        }

        return UserDetailsImpl(client.emailOfClient, client.password, mutableListOf<GrantedAuthority>(SimpleGrantedAuthority(Role.Client.toString())))
    }
}