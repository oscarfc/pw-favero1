/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tss.pw.favero1.users;

import java.util.Collection;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
     * @param search
     * @return Collection<User>
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response all(@QueryParam("search") String search) {
        Collection<User> lista
                = search == null || search.isEmpty() ? store.all() : store.search(search);
        if (lista != null && !lista.isEmpty()) {
            return Response.status(Response.Status.FOUND)
                    .entity(lista)
                    .build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(User u) {
        if (u.getId() == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .header("caused by", "id is null")
                    .build();
        }
        User user = store.create(u);
        return Response.status(Response.Status.CREATED)
                .entity(user)
                .build();
    }

    /**
     *
     * @param id Long
     * @param fname String
     * @param lname String
     * @param usr String
     * @param pwd String
     * @return User
     */
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(
            @FormParam("id") Long id,
            @FormParam("firstName") String fname,
            @FormParam("lastName") String lname,
            @FormParam("usr") String usr,
            @FormParam("pwd") String pwd) {
        User user = new User(id, usr, pwd);
        user.setFirstName(fname);
        user.setLastName(lname);
        store.create(user);
        return Response.status(Response.Status.CREATED)
                .entity(user)
                .build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("id") Long id) {
        User u = store.find(id);
        if (u != null) {
            return Response.status(Response.Status.FOUND)
                    .entity(u)
                    .build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User update(User u) {
        return store.update(u);
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        store.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
