package com.eproject.webapi.usercontroller;

import java.math.BigDecimal;
import java.sql.Date;

public class BookTicketRequestDetail {
    public String customerName;
    public Date customerDob;
    public String customerIc;
    public String customerEmail;
    public String customerPhone;
    public String pickupPoint;
    public String dropoffPoint;
    public String seatNumber;
    public BigDecimal price;
}
