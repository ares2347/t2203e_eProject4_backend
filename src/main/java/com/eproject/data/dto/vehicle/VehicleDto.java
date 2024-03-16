package com.eproject.data.dto.vehicle;

import com.eproject.data.model.tripmodel.TripStatusEnum;
import com.eproject.data.model.vehiclemodel.VehicleTypeEnum;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.UUID;

public class VehicleDto {
    private UUID vehicleId;

    private VehicleTypeEnum vehicleType;

    private String vehicleBrand;

    private String licensePlate;

    private Integer seatAmount;

    private String currentStation;

    private TripStatusEnum vehicleStatus;

    private String photoUrl;
}
