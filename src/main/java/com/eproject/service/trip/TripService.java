package com.eproject.service.trip;

import com.eproject.data.ticketmodel.TicketConfigEntity;
import com.eproject.data.tripmodel.TripConfigEntity;
import com.eproject.data.tripmodel.TripDto;
import com.eproject.data.tripmodel.TripEntity;
import com.eproject.data.vehiclemodel.SeatConfigEntity;
import com.eproject.data.vehiclemodel.VehicleConfigEntity;
import com.eproject.repository.trip.ITripConfigRepository;
import com.eproject.repository.trip.ITripRepository;
import com.eproject.service.vehicle.IVehicleService;
import com.eproject.service.vehicle.VehicleService;
import com.eproject.webapi.brandcontroller.CreateTripConfigRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TripService implements ITripService {
    @Autowired
    private ITripRepository _tripRepository;
    @Autowired
    private ITripConfigRepository _tripConfigRepository;
    @Autowired
    private VehicleService _vehicleService;

    @Override
    public Page<TripEntity> getList(String sortBy, String sort, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, sort.equals("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending());
        return _tripRepository.findAll(pageable);
    }

    @Override
    public Page<TripEntity> getList(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return _tripRepository.findAll(pageable);
    }
    @Override
    public Page<TripDto> getList(String departFrom, String arriveTo, Date departAt, String sortBy, String sort, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, sort.equals("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending());
        return _tripRepository.getTripsByParams(departFrom, arriveTo, departAt, pageable);
    }

    @Override
    public TripEntity getDetail(UUID id) {
        return _tripRepository.findById(id).orElseThrow();
    }

    @Override
    public TripConfigEntity getTripConfig(UUID id) {
        return _tripConfigRepository.findById(id).orElseThrow();
    }

    @Override
    public Page<TripConfigEntity> getConfigList(String departFrom, String arriveTo, Date departAt, String sortBy, String sort, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, sort.equals("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending());
        return _tripConfigRepository.getTripConfigsByParams(departFrom, arriveTo, departAt, pageable);
    }

    @Override
    public TripConfigEntity addTripConfig(CreateTripConfigRequest request) {
        VehicleConfigEntity vehicleConfig = _vehicleService.getVehicleConfigById(request.vehicleId);
        if(vehicleConfig == null){
            return null;
        }
        TripConfigEntity tripConfigEntity = new TripConfigEntity(
                request.departFrom,
                request.departAt,
                request.arriveTo,
                request.arriveAt,
                request.stops,
                vehicleConfig,
                request.isRepeated,
                request.ticketConfigs.stream().map(x ->
                        new TicketConfigEntity(
                                x.ticketType,
                                x.price,
                                x.seat,
                                x.coach,
                                x.ticketDescription)).collect(Collectors.toList())
                );

        return _tripConfigRepository.save(tripConfigEntity);
    }
}
