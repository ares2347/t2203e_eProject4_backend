package com.eproject.webapi.authcontroller;

import jakarta.validation.constraints.Pattern;

public class RegisterRequest {
    public String password;
    @Pattern(regexp = "[a-z0-9]+@[a-z]+\\.[a-z]{2,3}",
            message = "Email must be a valid email address")
    public String email;
    @Pattern(regexp = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$",
            message = "Phone must be a valid phone number")
    public String phone;
    public String fullName;
}
