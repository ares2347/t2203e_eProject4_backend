package com.eproject.data.dto.referencedata;

import com.fasterxml.jackson.annotation.JsonCreator;

public class StationParameterDetail {
    private final String City;
    private final String Address;
    private final String Phone;

    @JsonCreator
    public StationParameterDetail(String city, String address, String phone) {
        City = city;
        Address = address;
        Phone = phone;
    }

    public String getCity() {
        return City;
    }

    public String getAddress() {
        return Address;
    }

    public String getPhone() {
        return Phone;
    }
}
