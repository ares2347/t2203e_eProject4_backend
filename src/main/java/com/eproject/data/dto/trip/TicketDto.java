package com.eproject.data.dto.trip;

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
}
