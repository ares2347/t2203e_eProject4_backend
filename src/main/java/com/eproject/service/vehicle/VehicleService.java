package com.eproject.service.vehicle;

import com.eproject.data.dto.PageDto;
import com.eproject.data.dto.vehicle.VehicleDto;
import com.eproject.data.model.brandmodel.BrandEntity;
import com.eproject.data.model.brandmodel.DriverEntity;
import com.eproject.data.model.usermodel.UserEntity;
import com.eproject.data.model.vehiclemodel.VehicleEntity;
import com.eproject.repository.BrandRepository;
import com.eproject.repository.DriverRepository;
import com.eproject.repository.VehicleRepository;
import com.eproject.service.auth.JwtService;
import com.eproject.webapi.brandcontroller.CreateVehicleRequest;
import com.eproject.webapi.brandcontroller.UpdateDriverVehicle;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService implements IVehicleService {

    @Autowired
    VehicleRepository _vehicleRepository;
    @Autowired
    BrandRepository _brandRepository;
    @Autowired
    DriverRepository _driverRepository;
    @Autowired
    JwtService _jwtService;
    @Autowired
    ModelMapper _modelMapper;

    @Override
    public VehicleDto createNewVehicle(CreateVehicleRequest request) {
        VehicleEntity vehicle = _modelMapper.map(request, VehicleEntity.class);
        UserEntity user = _jwtService.getCurrentUser();
        BrandEntity brand = _brandRepository.findById(user.getBrand().getBrandId()).orElseThrow();
        vehicle.setBrand(brand);
        VehicleEntity res = _vehicleRepository.saveAndFlush(vehicle);
        return _modelMapper.map(res, VehicleDto.class);
    }

    @Override
    public PageDto<VehicleDto> getVehiclesByCurrentUser(String sortBy, String sortOrder, int page, int size) {
        try{
            UserEntity user = _jwtService.getCurrentUser();
            BrandEntity brand = _brandRepository.findById(user.getBrand().getBrandId()).orElseThrow();
            Pageable pagination;
            if (sortBy.isEmpty()) {
                pagination = PageRequest.of(page, size);
            } else {
                if (sortOrder.equals("asc")) {
                    pagination = PageRequest.of(page, size, Sort.by(sortBy).ascending());
                } else {
                    pagination = PageRequest.of(page, size, Sort.by(sortBy).descending());
                }
            }
            Page<VehicleEntity> queryResult = _vehicleRepository.findAllByBrand(brand, pagination);
            return new PageDto<VehicleDto>(
                    queryResult.get().map(x -> _modelMapper.map(x, VehicleDto.class)).toList(),
                    queryResult.getNumber(),
                    queryResult.getSize(),
                    queryResult.getTotalPages(),
                    queryResult.getNumberOfElements());
        }catch (Exception ex){
            ex.printStackTrace();
            return PageDto.empty();
        }
    }

    @Override
    public VehicleDto updateVehicleDrivers(UpdateDriverVehicle request) {
        VehicleEntity vehicle = _vehicleRepository.findById(request.vehicleId).orElseThrow();
        List<DriverEntity> driverEntities = _driverRepository.findAllByDriverIdIn(request.driverIds);
        vehicle.getDrivers().addAll(driverEntities);
        VehicleEntity result = _vehicleRepository.saveAndFlush(vehicle);
        return _modelMapper.map(result, VehicleDto.class);
    }
}
