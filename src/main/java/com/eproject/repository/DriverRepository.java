package com.eproject.repository;

import com.eproject.data.model.brandmodel.DriverEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DriverRepository extends JpaRepository<DriverEntity, UUID> {
}
