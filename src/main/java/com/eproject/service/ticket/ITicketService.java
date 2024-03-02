package com.eproject.service.ticket;

import com.eproject.data.ticketmodel.TicketConfigEntity;
import com.eproject.data.ticketmodel.TicketEntity;
import com.eproject.webapi.usercontroller.BookTicketBulkRequest;
import com.eproject.webapi.usercontroller.BookTicketRequest;

import java.util.List;
import java.util.UUID;

public interface ITicketService {
    List<TicketEntity> bookTicket(BookTicketBulkRequest request);
   TicketEntity bookTicket(BookTicketRequest request);
   List<TicketConfigEntity> getTicketConfigByTripConfigId(UUID tripConfigId);

   List<TicketEntity> getTicketByTripId(UUID tripId);
}
