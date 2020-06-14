package com.natasha.mmzapp.infrastructure.services

import com.natasha.mmzapp.domain.core.repositories.client.SpringClientRepository
import com.natasha.mmzapp.infrastructure.helpers.PasswordProcessor
import com.natasha.mmzapp.infrastructure.models.UserDetailsImpl
import com.natasha.mmzapp.infrastructure.repositories.ClientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component

@Component
class JwtUserDetailsService(@Autowired val clientRepository: ClientRepository, @Autowired val passwordProcessor: PasswordProcessor) : UserDetailsService {
    private val adminEmail: String = "admin@mail.ru"
    private val adminPass: String = "admin123"

    override fun loadUserByUsername(username: String?): UserDetails {
        if (username == null){
            throw UsernameNotFoundException(username)
        }

        if (username == adminEmail){
            return UserDetailsImpl(adminEmail, passwordProcessor.getHashOfPassword(adminPass), mutableListOf<GrantedAuthority>())
        }

        val client = username.let { clientRepository.getByEmail(it) }

        return UserDetailsImpl(client.emailOfClient, client.password, mutableListOf<GrantedAuthority>())
    }
}