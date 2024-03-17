package com.eproject.webapi.authcontroller;

import jakarta.validation.constraints.Pattern;

public class RegisterRequest {
    public String password;
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$",
            message = "Email must be a valid email address")
    public String email;
    @Pattern(regexp = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$",
            message = "Phone must be a valid phone number")
    public String phone;
    public String fullName;
}
