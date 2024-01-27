package com.eproject.webapi.authcontroller;

import java.sql.Time;

public class AuthResponse {
    public String accessToken;
    public Time expired;

    public AuthResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public AuthResponse() {
    }
}
