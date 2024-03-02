package com.eproject.service.vehicle;

import com.eproject.data.vehiclemodel.VehicleConfigEntity;
import com.eproject.webapi.brandcontroller.CreateVehicleConfigRequest;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface IVehicleService {
    VehicleConfigEntity getVehicleConfigById(UUID id);

    Page<VehicleConfigEntity> getList(UUID brandId, String sortBy, String sort, int page, int size);

    VehicleConfigEntity addConfig(CreateVehicleConfigRequest request);

    Page<VehicleConfigEntity> getBrandVegicleConfifgList(String sortBy, String sort, int page, int size);
    List<VehicleConfigEntity> getBrandVegicleConfifgList();
}
