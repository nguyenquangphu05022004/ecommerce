package com.example.ecommerce.domain.dto;

import java.util.HashMap;
import java.util.Map;

public class UserTrack {
    private static UserTrack userTrack = null;
    private Map<String, Boolean> map;
    private UserTrack(Map<String, Boolean> map) {
        this.map = map;
    }

    public Map<String, Boolean> getMap() {
        return map;
    }
    public static UserTrack getInstance() {
        if(userTrack == null)  {
            userTrack = new UserTrack(new HashMap<>());
        }
        return userTrack;
    }
}
