package com.eproject.webapi.usercontroller;

import com.eproject.data.ticketmodel.TicketEntity;
import com.eproject.data.vehiclemodel.VehicleConfigEntity;
import com.eproject.service.ticket.TicketService;
import com.eproject.webapi.brandcontroller.CreateVehicleConfigRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    TicketService _ticketService;
    @PostMapping(path = "/ticket/book", consumes = "application/json", produces = "application/json")
    public ResponseEntity<List<TicketEntity>> bookTicket(@RequestBody BookTicketBulkRequest request){
        try {
            List<TicketEntity> result = _ticketService.bookTicket(request);
            return new ResponseEntity<List<TicketEntity>>(result, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<List<TicketEntity>>(new ArrayList<TicketEntity>(), HttpStatus.BAD_REQUEST);
        }
    }
}
