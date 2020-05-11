/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tss.pw.favero.security;

import it.tss.pw.favero1.users.UserStore;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author oscar.favero
 */
@Path(value = "/authentication")
public class AuthenticationResource {
    @Inject
    UserStore store;

    /**
     *
     * @param credential Credential
     * @return Response
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(Credential credential) {
        if (store.search(credential).isPresent()) {
            return Response.status(Response.Status.OK)
                            .header("token", "ok")
                            .build();
        }
        else {
            return Response.status(Response.Status.UNAUTHORIZED)
                            .header("token", "ko")
                            .build();       
        }
    }

    /**
     *
     * @param usr String
     * @param pwd String
     * @return Response
     */
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@FormParam("usr") String usr, 
                            @FormParam("pwd") String pwd) {

    if (store.search(new Credential(usr, pwd)).isPresent()) {
            return Response.status(Response.Status.OK)
                            .header("token", "ok")
                            .build();
        }
        else {
            return Response.status(Response.Status.UNAUTHORIZED)
                            .header("token", "ko")
                            .build();       
        }
    }
}
