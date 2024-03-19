package com.eproject.data.dto.brand;

import com.eproject.data.model.brandmodel.DriverEntity;

import java.time.LocalDate;

public class DriverDto {
    private String fullName;
    private LocalDate dob;
    private String phoneNumber;
    private String email;
    private String nationalId;
    private String address;
    private String photoUrl;

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
