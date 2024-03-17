package com.eproject.webapi.admincontroller;

import jakarta.validation.constraints.Pattern;

public class CreateBrandAccountRequest {
    public String brandName;
    @Pattern(regexp = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$",
            message = "Hotline must be a valid phone number")
    public String hotline;
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$",
            message = "Email must be a valid email address")
    public String email;

}
