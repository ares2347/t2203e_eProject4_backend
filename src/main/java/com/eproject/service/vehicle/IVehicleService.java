package com.eproject.service.vehicle;

import com.eproject.data.dto.PageDto;
import com.eproject.data.dto.vehicle.VehicleDto;
import com.eproject.webapi.brandcontroller.CreateVehicleRequest;
import com.eproject.webapi.brandcontroller.UpdateDriverVehicle;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface IVehicleService {
    VehicleDto createNewVehicle(CreateVehicleRequest request);
    PageDto<VehicleDto> getVehiclesByCurrentUser(String sortBy, String sortOrder, int page, int size);
    VehicleDto updateVehicleDrivers(UpdateDriverVehicle request);
}
