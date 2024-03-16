package com.eproject.repository;

import com.eproject.data.model.ticketmodel.TicketEntity;
import com.eproject.data.model.tripmodel.TripStatusEnum;
import com.eproject.data.model.vehiclemodel.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface VehicleRepository extends JpaRepository<VehicleEntity, UUID> {
    List<VehicleEntity> findAllByCurrentStationAndPreviousStationNull(String currentStation);
}
