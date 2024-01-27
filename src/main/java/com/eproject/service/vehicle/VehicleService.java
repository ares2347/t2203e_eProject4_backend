package com.eproject.service.vehicle;

import com.eproject.data.usermodel.BrandEntity;
import com.eproject.data.vehiclemodel.SeatConfigEntity;
import com.eproject.data.vehiclemodel.VehicleConfigEntity;
import com.eproject.repository.user.IBrandRepository;
import com.eproject.repository.vehicle.IVehicleConfigRepository;
import com.eproject.repository.vehicle.IVehicleRepository;
import com.eproject.service.auth.JwtService;
import com.eproject.webapi.brandcontroller.CreateVehicleConfigRequest;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class VehicleService implements IVehicleService {
    @Autowired
    private IVehicleRepository _vehicleRepository;
    @Autowired
    private IVehicleConfigRepository _vehicleConfigRepository;
    @Autowired
    private IBrandRepository _brandRepository;
    @Autowired
    private JwtService _jwtService;

    @Override
    @Nullable
    public VehicleConfigEntity getVehicleConfigById(UUID id) {
        return _vehicleConfigRepository.findById(id).orElse(null);
    }

    @Override
    @Nullable
    public Page<VehicleConfigEntity> getList(UUID brandId, String sortBy, String sort, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, sort.equals("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending());
        BrandEntity brand = _brandRepository.findById(brandId).orElse(null);
        if (brand == null) return null;
        return _vehicleConfigRepository.findVehicleConfigEntitiesByBrand(brand, pageable);
    }

    @Override
    @Nullable
    public Page<VehicleConfigEntity> getBrandVegicleConfifgList(String sortBy, String sort, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, sort.equals("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending());
        BrandEntity brand = _brandRepository.findBrandEntityByUser(_jwtService.getCurrentUser());
        if (brand == null) return null;
        return _vehicleConfigRepository.findVehicleConfigEntitiesByBrand(brand, pageable);
    }

    @Override
    public VehicleConfigEntity addConfig(CreateVehicleConfigRequest request) {
        BrandEntity brand = _brandRepository.findBrandEntityByUser(_jwtService.getCurrentUser());
        if (brand == null) return null;
        VehicleConfigEntity vehicleConfigEntity = new VehicleConfigEntity(
                request.vehicleType,
                request.vehicleName,
                request.seatAmount,
                request.seatConfig.stream().map(x -> new SeatConfigEntity(x.seat, x.coach)).collect(Collectors.toList()),
                brand,
                request.thumbnailUrl,
                request.imageUrls
        );

        return _vehicleConfigRepository.save(vehicleConfigEntity);
    }

}
