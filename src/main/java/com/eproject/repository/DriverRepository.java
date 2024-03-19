package com.eproject.repository;

import com.eproject.data.model.brandmodel.BrandEntity;
import com.eproject.data.model.brandmodel.DriverEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface DriverRepository extends JpaRepository<DriverEntity, UUID> {
    Page<DriverEntity> findAllByBrand(BrandEntity brand, Pageable pageable);
    List<DriverEntity> findAllByDriverIdIn(Collection<UUID> driverId);
}
