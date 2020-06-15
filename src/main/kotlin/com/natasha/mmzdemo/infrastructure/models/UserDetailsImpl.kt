package com.natasha.mmzdemo.infrastructure.models

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserDetailsImpl(_userName: String,
                      _password: String,
                      _authorities: MutableList<out GrantedAuthority>) : UserDetails {

    private val username: String = _userName
    private val password: String = _password
    private val authorities: MutableList<out GrantedAuthority> = _authorities
    private val isEnabled: Boolean = true

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return authorities
    }

    override fun isEnabled(): Boolean {
        return isEnabled
    }

    override fun getUsername(): String {
        return username
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun getPassword(): String {
        return password
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }
}