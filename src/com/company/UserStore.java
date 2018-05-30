package com.company;

import java.util.HashMap;
import java.util.Map;

public class UserStore {
    private Map<String, User> userStoreMap = new HashMap<String, User>();

    public void registerUser(User newUser) {
        userStoreMap.put(newUser.getName(), newUser);

    }

    public User login(String name, String password) {
        if(userStoreMap.containsKey(name) && userStoreMap.get(name).getPassword().equals(password)) {
            return userStoreMap.get(name);
        }
        return null;
    }

    public boolean isValidName(String name) {
        return !userStoreMap.containsKey(name);
    }

    public void editUser(User user) {
        userStoreMap.put(user.getName(), user);
    }

    public void printUserStoreMap() {
        for(Map.Entry<String, User> user: userStoreMap.entrySet()) {
            System.out.println(user.toString());
        }
    }

}
