package com.eproject.service.vehicle;

import com.eproject.data.vehiclemodel.VehicleConfigEntity;
import com.eproject.data.vehiclemodel.VehicleEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface IVehicleService {
    VehicleConfigEntity getVehicleConfigById(UUID id);
}
