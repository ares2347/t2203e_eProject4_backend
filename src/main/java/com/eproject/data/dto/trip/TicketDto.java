package com.eproject.data.dto.trip;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class TicketDto {
    private UUID ticketId;
    private String customerName;
    private Date customerDob;
    private String customerIc;
    private String customerEmail;
    private String customerPhone;
    private String pickupPoint;
    private String dropoffPoint;
    private String seatNumber;
    private BigDecimal price;
    private LocalTime startTime;
    private LocalDate startDate;
}
