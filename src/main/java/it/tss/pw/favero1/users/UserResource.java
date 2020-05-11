/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tss.pw.favero1.users;

import java.util.Collection;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

/**
 *
 * @author oscar.favero
 */
@Path(value = "/users")
public class UserResource {

    @Inject
    UserStore store;

    /**
     *
     * @return Collection<User>
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<User> all() {
        return store.all();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User create(User u) {
       User user = store.create(u);
       return user;
    }

    /**
     *
     * @param fname String
     * @param lname String
     * @param usr String
     * @param pwd String
     * @return User
     */
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public User create(
            @FormParam("firstName") String fname,
            @FormParam("lastName") String lname,
            @FormParam("usr") String usr,
            @FormParam("pwd") String pwd) {
        User user = new User(null, usr, pwd);
        user.setFirstName(fname);
        user.setLastName(lname);
        User saved = store.create(user);
        return saved;
    }
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User find(@PathParam("id") Long id) {
        return store.find(id);
    }
}
