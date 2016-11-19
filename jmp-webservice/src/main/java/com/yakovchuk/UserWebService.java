package com.yakovchuk;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.util.HashMap;

@Path("user")
public class UserWebService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(@QueryParam("id") final long id) {
        return getUsers().get(id);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public User deleteUser(@QueryParam("id") final long id) {
        return getUsers().remove(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public User updateUser(final User user) {
        if(getUsers().containsKey(user.getId())) {
            return getUsers().put(user.getId(), user);
        } else {
            return null;
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User createUser(final User user) {
        return getUsers().put(user.getId(), user);
    }

    private HashMap<Long, User> getUsers() {
        return Repository.getInstance().getUsers();
    }
}

