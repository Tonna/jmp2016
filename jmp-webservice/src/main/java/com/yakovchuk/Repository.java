package com.yakovchuk;

import java.util.HashMap;


final class Repository {
    private static Repository instance;
    private HashMap<Long, User> users;

    private Repository() {
    }

    static synchronized Repository getRepository() {
        if (instance == null) {
            instance = new Repository();
            instance.users = new HashMap<>();
        }
        return instance;
    }

    HashMap<Long, User> getUsers() {
        return users;
    }
}
