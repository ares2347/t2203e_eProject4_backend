package com.eproject.service.vehicle;

import com.eproject.data.vehiclemodel.VehicleConfigEntity;
import com.eproject.repository.vehicle.IVehicleConfigRepository;
import com.eproject.repository.vehicle.IVehicleRepository;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class VehicleService implements IVehicleService{
    @Autowired
    private IVehicleRepository _vehicleRepository;
    @Autowired
    private IVehicleConfigRepository _vehicleConfigRepository;

    @Override
    @Nullable
    public VehicleConfigEntity getVehicleConfigById(UUID id) {
        return _vehicleConfigRepository.findById(id).orElse(null);
    }
}
