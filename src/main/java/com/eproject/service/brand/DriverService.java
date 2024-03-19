package com.eproject.service.brand;

import com.eproject.data.dto.PageDto;
import com.eproject.data.dto.brand.DriverDto;
import com.eproject.data.dto.vehicle.VehicleDto;
import com.eproject.data.model.brandmodel.BrandEntity;
import com.eproject.data.model.brandmodel.DriverEntity;
import com.eproject.data.model.usermodel.UserEntity;
import com.eproject.data.model.vehiclemodel.VehicleEntity;
import com.eproject.repository.BrandRepository;
import com.eproject.repository.DriverRepository;
import com.eproject.repository.VehicleRepository;
import com.eproject.service.auth.JwtService;
import com.eproject.webapi.brandcontroller.CreateDriverRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class DriverService implements IDriverService {
    @Autowired
    ModelMapper _modelMapper;
    @Autowired
    DriverRepository _driverRepository;
    @Autowired
    BrandRepository _brandRepository;
    @Autowired
    JwtService _jwtService;

    @Override
    public DriverDto createDriver(CreateDriverRequest request) {
        DriverEntity driver = _modelMapper.map(request, DriverEntity.class);
        UserEntity user = _jwtService.getCurrentUser();
        BrandEntity brand = _brandRepository.findById(user.getBrand().getBrandId()).orElseThrow();
        driver.setBrand(brand);
        DriverEntity res = _driverRepository.saveAndFlush(driver);
        return _modelMapper.map(res, DriverDto.class);
    }

    @Override
    public PageDto<DriverDto> getDriversByCurrentUser(String sortBy, String sortOrder, int page, int size) {
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
            Page<DriverEntity> queryResult = _driverRepository.findAllByBrand(brand, pagination);
            return new PageDto<DriverDto>(
                    queryResult.get().map(x -> _modelMapper.map(x, DriverDto.class)).toList(),
                    queryResult.getNumber(),
                    queryResult.getSize(),
                    queryResult.getTotalPages(),
                    queryResult.getNumberOfElements());
        }catch (Exception ex){
            ex.printStackTrace();
            return PageDto.empty();
        }
    }
}
