package com.eproject.data.tripmodel;

import com.eproject.data.vehiclemodel.VehicleTypeEnum;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalTime;
import java.util.UUID;

public interface TripDto {

    @Value("#{@binaryToUuidConverter.convert(target.tripId)}")
    UUID getTripId();

    @Value("#{@binaryToUuidConverter.convert(target.tripConfigId)}")
    UUID getTripConfigId();

    String getTripStatus();

    Date getDepartDate();

    Integer getSeatRemains();

    String getDriverEmail();

    String getDriverPhone();

    String getDriverName();

    String getDepartFrom();

    LocalTime getDepartAt();

    String getArriveTo();

    LocalTime getArriveAt();

    BigDecimal getPrice();

    Integer getSeatAmount();

    @Value("#{@byteToVehicleTypeConverter.convert(target.vehicleType)}")
    VehicleTypeEnum getVehicleType();

    String getBrandName();
}
