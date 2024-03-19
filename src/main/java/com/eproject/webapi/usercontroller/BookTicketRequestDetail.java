package com.eproject.webapi.usercontroller;

import java.math.BigDecimal;
import java.sql.Date;

public class BookTicketRequestDetail {
    private String customerName;
    private Date customerDob;
    private String customerIc;
    private String customerEmail;
    private String customerPhone;
    private String pickupPoint;
    private String dropoffPoint;
    private String seatNumber;
    private BigDecimal price;
}
