package com.eproject.data.dto.vehicle;

import com.eproject.data.model.tripmodel.TripStatusEnum;
import com.eproject.data.model.vehiclemodel.VehicleEntity;
import com.eproject.data.model.vehiclemodel.VehicleTypeEnum;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.UUID;

public class VehicleDto {
    public UUID vehicleId;

    public VehicleTypeEnum vehicleType;

    public String vehicleBrand;

    public String licensePlate;

    public Integer seatAmount;

    public String currentStation;

    public TripStatusEnum vehicleStatus;

    public String photoUrl;

    public VehicleDto(VehicleEntity entity) {
        this.vehicleId = entity.getVehicleId();
        this.vehicleType = entity.getVehicleType();
        this.vehicleBrand = entity.getVehicleBrand();
        this.licensePlate = entity.getLicensePlate();
        this.seatAmount = entity.getSeatAmount();
        this.currentStation = entity.getCurrentStation();
        this.vehicleStatus = entity.getVehicleStatus();
        this.photoUrl = entity.getPhotoUrl();
    }
}
