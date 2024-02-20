package com.eproject.webapi.usercontroller;

import com.eproject.data.ticketmodel.TicketEntity;
import com.eproject.data.tripmodel.TripEntity;
import com.eproject.data.usermodel.UserDto;
import com.eproject.data.usermodel.UserEntity;
import com.eproject.data.vehiclemodel.VehicleConfigEntity;
import com.eproject.service.auth.JwtService;
import com.eproject.service.ticket.TicketService;
import com.eproject.service.user.IUserService;
import com.eproject.service.user.UserService;
import com.eproject.webapi.brandcontroller.CreateVehicleConfigRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    TicketService _ticketService;

    @Autowired
    private JwtService _jwtService;

    @Autowired
    private UserService _userService;

    @PostMapping(path = "/ticket/book", consumes = "application/json", produces = "application/json")
    public ResponseEntity<List<TicketEntity>> bookTicket(@RequestBody BookTicketBulkRequest request){
        try {
            List<TicketEntity> result = _ticketService.bookTicket(request);
            return new ResponseEntity<List<TicketEntity>>(result, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<List<TicketEntity>>(new ArrayList<TicketEntity>(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/info")
    public ResponseEntity<UserDto> getUserInfo(HttpServletRequest request) {
        try {
            String authHeader = request.getHeader("Authorization");
            String username = _jwtService.getUsernameFromToken(authHeader.substring(7));
            UserEntity user = _userService.getUserByUsername(username);
            return new ResponseEntity<UserDto>(new UserDto(user), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<UserDto>(new UserDto(), HttpStatus.BAD_REQUEST);
        }
    }
}
