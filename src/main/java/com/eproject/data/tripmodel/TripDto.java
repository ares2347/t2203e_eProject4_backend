package com.eproject.data.tripmodel;

import com.eproject.data.ticketmodel.TicketEntity;
import com.eproject.data.vehiclemodel.VehicleEntity;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.UUID;

public class TripDto {
    public UUID tripId;
    public UUID tripConfigId;
    public TripStatusEnum tripStatus;
    public Date departDate;
    public int seatRemains;
    public String driverEmail;
    public String driverPhone;
    public String driverName;
    public String departFrom;
    public Time departAt;
    public String arriveTo;
    public Time arriveAt;
    public BigDecimal price;
}
