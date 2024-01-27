package com.eproject.webapi.brandcontroller;

import com.eproject.data.tripmodel.TripConfigEntity;
import com.eproject.data.vehiclemodel.VehicleTypeEnum;

import java.util.List;
import java.util.UUID;

public class CreateVehicleConfigRequest {
    public VehicleTypeEnum vehicleType;
    public String vehicleName;
    public int seatAmount;
    public List<SeatConfigRequest> seatConfig;
    public String thumbnailUrl;
    public String imageUrls;
}

