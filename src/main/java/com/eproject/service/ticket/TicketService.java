package com.eproject.service.ticket;

import com.eproject.data.ticketmodel.TicketConfigEntity;
import com.eproject.data.ticketmodel.TicketEntity;
import com.eproject.data.tripmodel.TripConfigEntity;
import com.eproject.data.tripmodel.TripEntity;
import com.eproject.repository.ticket.ITicketConfigRepository;
import com.eproject.repository.ticket.ITicketRepository;
import com.eproject.repository.trip.ITripConfigRepository;
import com.eproject.repository.trip.ITripRepository;
import com.eproject.webapi.usercontroller.BookTicketBulkRequest;
import com.eproject.webapi.usercontroller.BookTicketRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TicketService implements ITicketService {

    @Autowired
    ITicketRepository _ticketRepository;

    @Autowired
    ITicketConfigRepository _ticketConfiRepository;

    @Autowired
    ITripConfigRepository _tripConfigRepository;

    @Autowired
    ITripRepository _tripRepository;


    @Override
    public List<TicketEntity> bookTicket(BookTicketBulkRequest request, UUID userId) {
//        List<TicketConfigEntity> ticketConfigs = _ticketConfiRepository.findAllById(
//                request.tickets.stream().map(x -> x.ticketConfigId).toList());
        List<TicketEntity> tickets = new ArrayList<TicketEntity>();
        for (BookTicketRequest bookTicketRequest : request.tickets) {
            TripConfigEntity tripConfigEntity = _tripConfigRepository.getReferenceById(bookTicketRequest.tripConfigId);
            TripEntity tripEntity = tripConfigEntity.getTrips().stream().filter(trip -> trip.getTripId().equals(bookTicketRequest.tripId)).findAny().orElse(null);;
            if(tripEntity != null){
                TicketEntity ticket = new TicketEntity(
                        bookTicketRequest.customerName,
                        bookTicketRequest.customerDob,
                        bookTicketRequest.customerIc,
                        bookTicketRequest.customerEmail,
                        bookTicketRequest.customerPhone,
                        bookTicketRequest.pickupPoint,
                        bookTicketRequest.dropoffPoint,
                        tripEntity
                );
                ticket.setCreatedBy(userId);
                tickets.add(ticket);
            }else{
                TripEntity trip = new TripEntity();
                TripEntity rs = _tripRepository.save(new TripEntity(tripConfigEntity));
                TicketEntity ticket = new TicketEntity(
                        bookTicketRequest.customerName,
                        bookTicketRequest.customerDob,
                        bookTicketRequest.customerIc,
                        bookTicketRequest.customerEmail,
                        bookTicketRequest.customerPhone,
                        bookTicketRequest.pickupPoint,
                        bookTicketRequest.dropoffPoint,
                        rs
                );
                ticket.setCreatedBy(userId);
                tickets.add(ticket);
            }
//            TicketConfigEntity ticketConfig = ticketConfigs.stream().filter(x -> x.getTicketConfigId() == bookTicketRequest.ticketConfigId).findFirst().orElseThrow();
        }
        return _ticketRepository.saveAll(tickets);
    }

    @Override
    public TicketEntity bookTicket(BookTicketRequest request) {
        return null;
    }

    @Override
    public List<TicketConfigEntity> getTicketConfigByTripConfigId(UUID tripConfigId) {
        TripConfigEntity tripConfigEntity = _tripConfigRepository.getReferenceById(tripConfigId);
        return tripConfigEntity.getTicketConfigs();
    }

    @Override
    public List<TicketEntity> getTicketByTripId(UUID tripId) {
        TripEntity tripEntity = _tripRepository.getReferenceById(tripId);
        return tripEntity.getTickets();
    }

    @Override
    public List<TicketEntity> getUserTicket(UUID userId) {
        return _ticketRepository.getAllByCreatedBy(userId);
    }

    @Override
    public TicketEntity getUserTicketById(UUID userId, UUID ticketId) {
        return _ticketRepository.getFirstByTicketIdAndCreatedBy(ticketId, userId);
    }

}
