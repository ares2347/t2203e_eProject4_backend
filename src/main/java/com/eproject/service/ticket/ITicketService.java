package com.eproject.service.ticket;

import com.eproject.data.dto.PageDto;
import com.eproject.data.dto.trip.TicketDto;
import com.eproject.data.dto.trip.TripDto;
import com.eproject.webapi.usercontroller.BookTicketRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface ITicketService {
    PageDto<TicketDto> getCurrentUserTickets(LocalDate startDate, String dropoffPoint, String pickupPoint, int page, int size);
    List<TicketDto> bookTickets(BookTicketRequest request);
}
