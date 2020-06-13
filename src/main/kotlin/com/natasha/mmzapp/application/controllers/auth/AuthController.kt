package com.natasha.mmzapp.application.controllers.auth

import com.natasha.mmzapp.application.controllers.auth.dto.Client
import com.natasha.mmzapp.infrastructure.services.Auth
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.RequestBody
import javax.ws.rs.POST
import javax.ws.rs.PUT
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("api/auth")
@Component
class AuthController (@Autowired val auth: Auth){

    @Path("/reg")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    fun reg(@RequestBody client: Client): Response{
        auth.reg(client)
        return Response.ok().build()
    }
}