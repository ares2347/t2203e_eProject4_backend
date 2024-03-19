package com.eproject.repository;

import com.eproject.data.model.brandmodel.BrandEntity;
import com.eproject.data.model.ticketmodel.TicketEntity;
import com.eproject.data.model.tripmodel.TripStatusEnum;
import com.eproject.data.model.vehiclemodel.VehicleEntity;
import com.eproject.data.model.vehiclemodel.VehicleTypeEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface VehicleRepository extends JpaRepository<VehicleEntity, UUID> {
    List<VehicleEntity> findAllByCurrentStationAndPreviousStationNull(String currentStation);
    Page<VehicleEntity> findAllBySeatAmountAndVehicleType(Integer seatAmount, VehicleTypeEnum vehicleType, Pageable pageable);
    Page<VehicleEntity> findAllByBrand(BrandEntity brand, Pageable pageable);
}
