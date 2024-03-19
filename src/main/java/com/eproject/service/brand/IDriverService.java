package com.eproject.service.brand;

import com.eproject.data.dto.PageDto;
import com.eproject.data.dto.brand.DriverDto;
import com.eproject.data.dto.vehicle.VehicleDto;
import com.eproject.webapi.brandcontroller.CreateDriverRequest;
import org.springframework.stereotype.Service;

@Service
public interface IDriverService {
    DriverDto createDriver(CreateDriverRequest request);
    PageDto<DriverDto> getDriversByCurrentUser(String sortBy, String sortOrder, int page, int size);
}
