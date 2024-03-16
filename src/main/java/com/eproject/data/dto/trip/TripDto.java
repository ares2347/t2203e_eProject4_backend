package com.eproject.data.dto.trip;

import com.eproject.data.dto.brand.BrandDto;
import com.eproject.data.dto.vehicle.VehicleDto;
import com.eproject.data.model.brandmodel.BrandEntity;
import com.eproject.data.model.ticketmodel.TicketEntity;
import com.eproject.data.model.tripmodel.TripStatusEnum;
import com.eproject.data.model.vehiclemodel.VehicleEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.UUID;

public class TripDto {
    private UUID tripId;
    private TripStatusEnum tripStatus;
    private String startCity;
    private String startStation;
    private String endCity;
    private String endStation;
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime startTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate startDate;
    private Integer routeDuration;
    private String stationsMapping;
    private String vehicleType;
    private Integer seatAmount;
    private BrandDto brand;
    private VehicleDto vehicle;
    private MaskedTicketDto tickets;

}
