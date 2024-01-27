package com.eproject.repository.vehicle;

import com.eproject.data.usermodel.BrandEntity;
import com.eproject.data.vehiclemodel.VehicleConfigEntity;
import com.eproject.repository.IBaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

public interface IVehicleConfigRepository extends IBaseRepository<VehicleConfigEntity> {
    Page<VehicleConfigEntity> findVehicleConfigEntitiesByBrand(BrandEntity brand, Pageable pageable);
}
