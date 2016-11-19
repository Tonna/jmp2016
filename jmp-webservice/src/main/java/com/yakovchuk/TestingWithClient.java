package com.yakovchuk;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class TestingWithClient {

    public static void main(String[] args) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080").path("jmp-webservice/webapi/user");

        User user = new User();
        user.setId(1L);
        user.setLogin("first-user");
        user.setEmail("first-user@gmail.com");
        user.setFirstName("Mr. Herbert");
        user.setLastName("Harrison");
        user.setSex("Male");

        //getting not existing user
        System.out.println("Getting user");
        System.out.println(target.queryParam("id", "1").request().get(String.class));

        //creating user
        System.out.println("Creating user");
        System.out.println(target.request(MediaType.APPLICATION_JSON_TYPE).put(Entity.json(user)));

        //getting user
        System.out.println("Getting user");
        System.out.println(target.queryParam("id", "1").request().get(String.class));

        //updating user
        System.out.println("Updating user");
        user.setFirstName("Ms. Herberta");
        user.setSex("Female");
        System.out.println(target.request(MediaType.APPLICATION_XML_TYPE).post(Entity.xml(user)));

        //getting user
        System.out.println("Getting user");
        System.out.println(target.queryParam("id", "1").request().get(String.class));
    }
}
