package com.eproject.data.dto.trip;

import com.eproject.data.dto.brand.BrandDto;
import com.eproject.data.dto.vehicle.VehicleDto;
import com.eproject.data.model.brandmodel.BrandEntity;
import com.eproject.data.model.ticketmodel.TicketEntity;
import com.eproject.data.model.tripmodel.TripEntity;
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
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class TripDto {
    public UUID tripId;
    public TripStatusEnum tripStatus;
    public String startCity;
    public String startStation;
    public String endCity;
    public String endStation;
    @JsonFormat(pattern = "HH:mm:ss")
    public LocalTime startTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    public LocalDate startDate;
    @JsonFormat(pattern = "HH:mm:ss")
    public LocalTime routeDuration;
    public String stationsMapping;
    public String vehicleType;
    public Integer seatAmount;
    public BrandDto brand;
    public VehicleDto vehicle;
    public List<MaskedTicketDto> tickets;

    public TripDto(TripEntity entity){
        this.tripId = entity.getTripId();
        this.tripStatus = entity.getTripStatus();
        this.startCity = entity.getStartCity();
        this.startStation = entity.getStartStation();
        this.endCity = entity.getEndCity();
        this.endStation = entity.getEndStation();
        this.startTime = entity.getStartTime();
        this.startDate = entity.getStartDate();
        this.routeDuration = entity.getRouteDuration();
        this.stationsMapping = entity.getStationsMapping();
        this.vehicleType = entity.getVehicleType();
        this.seatAmount = entity.getSeatAmount();
        this.brand = new BrandDto(entity.getBrand());
        this.vehicle = new VehicleDto(entity.getVehicle());
        this.tickets = entity.getTickets().stream().map(x -> new MaskedTicketDto(x)).toList();
    }
}
