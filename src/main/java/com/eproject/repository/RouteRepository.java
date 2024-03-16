package com.eproject.repository;

import com.eproject.data.model.brandmodel.BrandEntity;
import com.eproject.data.model.ticketmodel.TicketEntity;
import com.eproject.data.model.tripmodel.RouteEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RouteRepository extends JpaRepository<RouteEntity, UUID> {
    Page<RouteEntity> findAllByBrand(BrandEntity brand, Pageable pageable);
}
