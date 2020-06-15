package com.natasha.mmzdemo.infrastructure.helpers

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.*
import kotlin.reflect.KFunction


@Component
class JwtTokenUtil {
    val JWT_TOKEN_VALIDITY = 5 * 60 * 60
    val secret = "super secret key"

    fun generateToken(userDetails: UserDetails): String? {
        val claims: Map<String, Any> = HashMap()
        return doGenerateToken(claims, userDetails.username)
    }

    private fun doGenerateToken(claims: Map<String, Any>, subject: String): String? {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(Date(System.currentTimeMillis()))
                .setExpiration(Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact()
    }

    fun getUsernameFromToken(jwtToken: String): String? {
        return getClaimFromToken(jwtToken, Claims::getSubject);
    }


    fun getExpirationDateFromToken(token: String?): Date? {
        return getClaimFromToken(token, Claims::getExpiration)
    }

    fun validateToken(jwtToken: String, userDetails: UserDetails): Boolean {
        val userName = getUsernameFromToken(jwtToken)

        return (userName.equals(userDetails.username) && !isTokenExpired(jwtToken))
    }

    private fun isTokenExpired(token: String): Boolean {
        val expiration = getExpirationDateFromToken(token)
        return expiration!!.before(Date())
    }

    fun <T> getClaimFromToken(token: String?, claimsResolver: KFunction<T>): T {
        val claims: Claims = getAllClaimsFromToken(token)
        return claimsResolver.call(claims)
    }

    private fun getAllClaimsFromToken(token: String?): Claims {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }
}
