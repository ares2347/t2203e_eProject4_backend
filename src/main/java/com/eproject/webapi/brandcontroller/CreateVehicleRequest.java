package com.eproject.webapi.brandcontroller;

import com.eproject.data.model.vehiclemodel.VehicleTypeEnum;

public class CreateVehicleRequest {
    private VehicleTypeEnum vehicleType;
    private String vehicleBrand;
    private String licensePlate;
    private Integer seatAmount;
    private String currentStation;
    private String photoUrl;
}
