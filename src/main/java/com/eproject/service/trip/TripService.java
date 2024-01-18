package com.eproject.service.trip;

import com.eproject.data.tripmodel.TripConfigEntity;
import com.eproject.data.tripmodel.TripEntity;
import com.eproject.data.vehiclemodel.VehicleConfigEntity;
import com.eproject.repository.trip.ITripConfigRepository;
import com.eproject.repository.trip.ITripRepository;
import com.eproject.service.vehicle.IVehicleService;
import com.eproject.webapi.tripcontroller.CreateTripConfigRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.List;
import java.util.UUID;

@Service
public class TripService implements ITripService {
    @Autowired
    private ITripRepository _tripRepository;
    @Autowired
    private ITripConfigRepository _tripConfigRepository;
    @Autowired
    private IVehicleService _vehicleService;

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
    public TripEntity getDetail(UUID id) {
        return _tripRepository.findById(id).orElseThrow();
    }

    @Override
    public TripConfigEntity getTripConfig(UUID id) {
        return _tripConfigRepository.findById(id).orElseThrow();
    }

    @Override
    public Page<TripConfigEntity> getConfigList(String sortBy, String sort, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, sort.equals("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending());
        return _tripConfigRepository.findAll(pageable);
    }

    @Override
    public Page<TripConfigEntity> getConfigList(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return _tripConfigRepository.findAll(pageable);
    }

    @Override
    public Page<TripConfigEntity> getConfigList(String sortBy, String sort, int page, int size, String departFrom, String arriveTo, Time departAt, Time arriveAt) {

        Pageable pageable = PageRequest.of(page, size, sort.equals("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending());
        return _tripConfigRepository.findAll(pageable);
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
                vehicleConfig
                );

        return _tripConfigRepository.save(tripConfigEntity);
    }
}
