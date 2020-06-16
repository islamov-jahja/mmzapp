package com.natasha.mmzdemo.middleware.security

import org.springframework.stereotype.Component

@Component
class AuthenticatedUser {
    private var userName: String = ""
    fun setUserName(_userName: String){
        userName = _userName
    }

    fun getUserName(): String{
        return userName
    }
}