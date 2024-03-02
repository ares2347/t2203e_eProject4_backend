package com.eproject.webapi.publiccontroller;

import com.eproject.data.ticketmodel.TicketConfigEntity;
import com.eproject.data.ticketmodel.TicketEntity;
import com.eproject.data.tripmodel.TripConfigEntity;
import com.eproject.service.ticket.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/public/ticket")
public class PublicTicketController {
    @Autowired
    ITicketService _ticketService;

    @GetMapping("/config/list/{tripConfigId}")
    public ResponseEntity<List<TicketConfigEntity>> getTicketConfigListByTripConfig(@PathVariable UUID tripConfigId) {
        try {
            List<TicketConfigEntity> result = _ticketService.getTicketConfigByTripConfigId(tripConfigId);
            return new ResponseEntity<List<TicketConfigEntity>>(result, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<List<TicketConfigEntity>>(new ArrayList<TicketConfigEntity>(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/list/{tripId}")
    public ResponseEntity<List<TicketEntity>> getTicketListByTrip(@PathVariable UUID tripId) {
        try {
            List<TicketEntity> result = _ticketService.getTicketByTripId(tripId);
            return new ResponseEntity<List<TicketEntity>>(result, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<List<TicketEntity>>(new ArrayList<TicketEntity>(), HttpStatus.BAD_REQUEST);
        }
    }
}
