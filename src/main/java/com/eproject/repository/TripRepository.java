package com.eproject.repository;

import com.eproject.data.model.brandmodel.BrandEntity;
import com.eproject.data.model.tripmodel.TripEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.UUID;

public interface TripRepository extends JpaRepository<TripEntity, UUID> {
    Page<TripEntity> findAllByStartDateAndStartCityAndEndCityAndVehicleTypeContains(LocalDate startDate, String startCity, String endCity, String vehicleType, Pageable pageable);
    Page<TripEntity> findAllByBrand(BrandEntity brand, Pageable pageable);
}
