package com.eproject.service.ticket;

import com.eproject.data.dto.PageDto;
import com.eproject.data.dto.trip.TicketDto;
import com.eproject.data.dto.trip.TripDto;
import com.eproject.data.model.ticketmodel.TicketEntity;
import com.eproject.data.model.tripmodel.TripEntity;
import com.eproject.data.model.usermodel.UserEntity;
import com.eproject.repository.TicketRepository;
import com.eproject.repository.TripRepository;
import com.eproject.service.auth.JwtService;
import com.eproject.webapi.usercontroller.BookTicketRequest;
import com.eproject.webapi.usercontroller.BookTicketRequestDetail;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TicketService implements ITicketService {

    @Autowired
    TicketRepository _ticketRepository;
    @Autowired
    TripRepository _tripRepository;
    @Autowired
    JwtService _jwtService;
    @Autowired
    ModelMapper _mapper;

    @Override
    public PageDto<TicketDto> getCurrentUserTickets(LocalDate startDate, String dropoffPoint, String pickupPoint, int page, int size) {
        try {
            UserEntity userEntity = _jwtService.getCurrentUser();
            Pageable pagination = PageRequest.of(page, size);
            Page<TicketEntity> queryResult = _ticketRepository.findAllByStartDateAndDropoffPointAndPickupPointAndCreatedBy(startDate, dropoffPoint, pickupPoint, userEntity, pagination);
            return new PageDto<TicketDto>(
                    queryResult.get().map(x -> _mapper.map(x, TicketDto.class)).toList(),
                    queryResult.getNumber(),
                    queryResult.getSize(),
                    queryResult.getTotalPages(),
                    queryResult.getNumberOfElements());
        } catch (Exception ex) {
            ex.printStackTrace();
            return PageDto.empty();
        }

    }

    @Override
    public List<TicketDto> bookTickets(BookTicketRequest request) {
        try {
            List<TicketEntity> ticketEntities = new ArrayList<>();
            TripEntity trip = _tripRepository.findById(request.tripId).orElseThrow();
            for (BookTicketRequestDetail ticket : request.tickets) {
                TicketEntity ticketEntity = _mapper.map(ticket, TicketEntity.class);
                ticketEntity.setTrip(trip);
                ticketEntity.setStartDate(trip.getStartDate());
                ticketEntity.setStartTime(trip.getStartTime());
                ticketEntities.add(ticketEntity);
            }
            List<TicketEntity> queryResult = _ticketRepository.saveAllAndFlush(ticketEntities);
            return queryResult.stream().map(x -> _mapper.map(x, TicketDto.class)).toList();
        } catch (Exception ex){
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }
}
