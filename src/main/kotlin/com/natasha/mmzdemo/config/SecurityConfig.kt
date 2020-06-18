package com.natasha.mmzdemo.config

import com.natasha.mmzdemo.infrastructure.models.Role
import com.natasha.mmzdemo.middleware.security.JwtAuthenticationEntryPoint
import com.natasha.mmzdemo.middleware.security.JwtRequestFilter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.stereotype.Component
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import java.util.*


@Component
@EnableWebSecurity
@ComponentScan("com.natasha.mmzdemo.middleware.security")
class SecurityConfig() : WebSecurityConfigurerAdapter() {


    @Autowired
    private val jwtAuthenticationEntryPoint: JwtAuthenticationEntryPoint? = null

    @Autowired
    private val jwtUserDetailsService: UserDetailsService? = null

    @Autowired
    private val jwtRequestFilter: JwtRequestFilter? = null

    @Bean
    @Throws(Exception::class)
    override fun authenticationManagerBean(): AuthenticationManager? {
        return super.authenticationManagerBean()
    }

    @Override
    protected override fun configure(http: HttpSecurity?) {
        if (http != null) {
            http.cors().and().csrf().disable().authorizeRequests()
                    .antMatchers(HttpMethod.POST,"/api/auth/reg").anonymous()
                    .antMatchers(HttpMethod.POST, "/api/auth/login").anonymous()
                    .antMatchers(HttpMethod.GET, "/api/auth/info", "/application", "/api/application/{id}/listSi", "/api/application").authenticated()
                    .antMatchers(HttpMethod.GET, "/api/auth/client/{id}").hasAuthority(Role.Admin.toString())
                    .antMatchers(HttpMethod.POST, "/api/application", "/api/application/{id}/reorganize").hasAuthority(Role.Client.toString())
                    .anyRequest().anonymous()
                    .and()
                    .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

            http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter().javaClass)
        }
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource? {
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/api/**", CorsConfiguration().applyPermitDefaultValues())
        return source
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder? {
        return BCryptPasswordEncoder()
    }

    @Autowired
    @Throws(java.lang.Exception::class)
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService<UserDetailsService>(jwtUserDetailsService).passwordEncoder(passwordEncoder())
    }
}