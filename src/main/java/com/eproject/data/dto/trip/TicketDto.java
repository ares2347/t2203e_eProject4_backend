package com.eproject.data.dto.trip;

import com.eproject.data.model.ticketmodel.TicketEntity;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class TicketDto {
    public UUID ticketId;
    public String customerName;
    public Date customerDob;
    public String customerIc;
    public String customerEmail;
    public String customerPhone;
    public String pickupPoint;
    public String dropoffPoint;
    public String seatNumber;
    public BigDecimal price;
    public LocalTime startTime;
    public LocalDate startDate;

    public TicketDto(TicketEntity entity) {
        this.ticketId = entity.getTicketId();
        this.customerName = entity.getCustomerName();
        this.customerDob = entity.getCustomerDob();
        this.customerIc = entity.getCustomerIc();
        this.customerEmail = entity.getCustomerEmail();
        this.customerPhone = entity.getCustomerPhone();
        this.pickupPoint = entity.getPickupPoint();
        this.dropoffPoint = entity.getDropoffPoint();
        this.seatNumber = entity.getSeatNumber();
        this.price = entity.getPrice();
        this.startTime = entity.getStartTime();
        this.startDate = entity.getStartDate();
    }
}
