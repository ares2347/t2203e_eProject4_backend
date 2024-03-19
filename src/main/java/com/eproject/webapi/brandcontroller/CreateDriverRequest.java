package com.eproject.webapi.brandcontroller;

import java.time.LocalDate;
import java.util.UUID;

public class CreateDriverRequest {
    public UUID driverId;
    public String fullName;
    public LocalDate dob;
    public String phoneNumber;
    public String email;
    public String nationalId;
    public String address;
    public String photoUrl;
}
