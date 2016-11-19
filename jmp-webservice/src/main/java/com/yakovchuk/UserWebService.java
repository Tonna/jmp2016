package com.yakovchuk;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import static com.yakovchuk.Repository.getRepository;

@Path("user")
public class UserWebService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(@QueryParam("id") final long id) {
        return getRepository().getUsers().get(id);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public User deleteUser(@QueryParam("id") final long id) {
        return getRepository().getUsers().remove(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public User updateUser(final User user) {
        if(getRepository().getUsers().containsKey(user.getId())) {
            return getRepository().getUsers().put(user.getId(), user);
        } else {
            return null;
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User createUser(final User user) {
        return getRepository().getUsers().put(user.getId(), user);
    }
}

