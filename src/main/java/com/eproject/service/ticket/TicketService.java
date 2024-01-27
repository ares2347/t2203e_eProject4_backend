package com.eproject.service.ticket;

import com.eproject.data.ticketmodel.TicketConfigEntity;
import com.eproject.data.ticketmodel.TicketEntity;
import com.eproject.repository.ticket.ITicketConfigRepository;
import com.eproject.repository.ticket.ITicketRepository;
import com.eproject.webapi.usercontroller.BookTicketBulkRequest;
import com.eproject.webapi.usercontroller.BookTicketRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TicketService implements ITicketService {

    @Autowired
    ITicketRepository _ticketRepository;
    ITicketConfigRepository _ticketConfiRepository;

    @Override
    public List<TicketEntity> bookTicket(BookTicketBulkRequest request) {
        List<TicketConfigEntity> ticketConfigs = _ticketConfiRepository.findAllById(
                request.tickets.stream().map(x -> x.ticketConfigId).toList());
        List<TicketEntity> tickets;
        for (BookTicketRequest bookTicketRequest : request.tickets) {
            TicketConfigEntity ticketConfig = ticketConfigs.stream().filter(x -> x.getTicketConfigId() == bookTicketRequest.ticketConfigId).findFirst().orElseThrow();
            TicketEntity ticket = new TicketEntity(
                    bookTicketRequest.customerName,
                    bookTicketRequest.customerDob,
                    bookTicketRequest.customerIc,
                    bookTicketRequest.customerEmail,
                    bookTicketRequest.customerPhone,
                    ticketConfig
            );
        }
        return null;
    }
}
