package com.eproject.data.tripmodel;

import com.eproject.data.ticketmodel.TicketEntity;
import com.eproject.data.vehiclemodel.VehicleEntity;

import java.util.List;
import java.util.UUID;

public class TripDto {
    private UUID tripId;
    private TripStatusEnum tripStatus;

    private String driverEmail;

    private String driverPhone;

    private String driverName;

    private TripConfigDto tripConfig;

    private VehicleEntity vehicle;

    List<TicketEntity> tickets;
}
