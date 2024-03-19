package com.eproject.data.dto.brand;

import com.eproject.data.model.brandmodel.DriverEntity;

import java.time.LocalDate;
import java.util.UUID;

public class DriverDto {
    public UUID driverId;
    public String fullName;
    public LocalDate dob;
    public String phoneNumber;
    public String email;
    public String nationalId;
    public String address;
    public String photoUrl;

    public DriverDto(DriverEntity entity) {
        this.fullName = entity.getFullName();
        this.dob = entity.getDob();
        this.phoneNumber = entity.getPhoneNumber();
        this.email = entity.getEmail();
        this.nationalId = entity.getNationalId();
        this.address = entity.getAddress();
        this.photoUrl = entity.getPhotoUrl();
    }
}
