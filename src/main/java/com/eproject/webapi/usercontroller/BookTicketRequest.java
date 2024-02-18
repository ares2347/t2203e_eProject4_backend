package com.eproject.webapi.usercontroller;

import jakarta.persistence.Column;

import java.sql.Date;
import java.util.UUID;

public class BookTicketRequest {
    public UUID ticketConfigId;
    public String customerName;
    public Date customerDob;
    public String customerIc;
    public String customerEmail;
    public String customerPhone;

}