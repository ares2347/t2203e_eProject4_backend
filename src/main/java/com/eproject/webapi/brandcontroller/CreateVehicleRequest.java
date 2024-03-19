package com.eproject.webapi.brandcontroller;

import com.eproject.data.model.vehiclemodel.VehicleTypeEnum;

public class CreateVehicleRequest {
    public VehicleTypeEnum vehicleType;
    public String vehicleBrand;
    public String licensePlate;
    public Integer seatAmount;
    public String currentStation;
    public String photoUrl;
}
