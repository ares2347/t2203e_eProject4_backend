package com.eproject.webapi.usercontroller;

import jakarta.persistence.Column;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

public class BookTicketRequest {
    public List<BookTicketRequestDetail> tickets;
    public UUID tripId;
}
