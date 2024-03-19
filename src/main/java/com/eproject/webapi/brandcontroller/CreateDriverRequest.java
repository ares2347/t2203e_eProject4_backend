package com.eproject.webapi.brandcontroller;

import java.time.LocalDate;
import java.util.UUID;

public class CreateDriverRequest {
    private UUID driverId;
    private String fullName;
    private LocalDate dob;
    private String phoneNumber;
    private String email;
    private String nationalId;
    private String address;
    private String photoUrl;
}
