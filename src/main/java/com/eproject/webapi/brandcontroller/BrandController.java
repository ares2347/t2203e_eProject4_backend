package com.eproject.webapi.brandcontroller;

import com.eproject.data.dto.PageDto;
import com.eproject.data.dto.brand.DriverDto;
import com.eproject.data.dto.trip.RouteDto;
import com.eproject.data.dto.vehicle.VehicleDto;
import com.eproject.data.model.tripmodel.RouteEntity;
import com.eproject.service.brand.DriverService;
import com.eproject.service.brand.IDriverService;
import com.eproject.service.trip.ITripService;
import com.eproject.service.trip.TripService;
import com.eproject.service.vehicle.IVehicleService;
import com.eproject.service.vehicle.VehicleService;
import com.eproject.webapi.BaseResponse;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/brand")
public class BrandController {
    @Autowired
    private TripService _tripService;
    @Autowired
    private VehicleService _vehicleService;
    @Autowired
    private DriverService _driverService;

    @Autowired
    private ModelMapper _mapper;

    //region Routes
    @PostMapping(path = "/trip/create-route", consumes = "application/json", produces = "application/json")
    public ResponseEntity createRoute(@RequestBody @Valid CreateRouteRequest request, BindingResult bindingResult) {
        try {
            RouteEntity routeEntity = _tripService.createNewRoute(request);
            RouteDto result = _mapper.map(routeEntity, RouteDto.class);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return new ResponseEntity<>(new BaseResponse("Tạo lộ trình thất bại."), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/trip/get-routes")
    public ResponseEntity getRoutes(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        try {
            PageDto<RouteDto> result = _tripService.getRoutesByCurrentUser(page, size);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return new ResponseEntity<>(new BaseResponse("Lỗi xảy ra khi tìm kiếm lộ trình."), HttpStatus.BAD_REQUEST);
        }
    }
    //endregion

    //region Trips

    @GetMapping(path = "/trip/get-trips")
    public ResponseEntity getTrips(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        try {
            PageDto<RouteDto> result = _tripService.getRoutesByCurrentUser(page, size);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return new ResponseEntity<>(new BaseResponse("Lỗi xảy ra khi tìm kiếm lộ trình."), HttpStatus.BAD_REQUEST);
        }
    }
    //endregion

    //region Drivers and Vehicles
    @PostMapping(path = "/vehicle/create-vehicle", consumes = "application/json", produces = "application/json")
    public ResponseEntity createVehicle(@RequestBody @Valid CreateVehicleRequest request, BindingResult bindingResult) {
        try {
            VehicleDto result = _vehicleService.createNewVehicle(request);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return new ResponseEntity<>(new BaseResponse("Tạo phương tiện thất bại."), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping(path = "/vehicle/get-vehicles")
    public ResponseEntity getVehicles(
            @RequestParam(name = "sortBy", defaultValue = "createdBy", required = false) String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = "desc", required = false) String sortOrder,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        try {
            PageDto<VehicleDto> result = _vehicleService.getVehiclesByCurrentUser(sortBy, sortOrder, page, size);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return new ResponseEntity<>(new BaseResponse("Lỗi xảy ra khi tìm kiếm phương tiện."), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/driver/create-driver", consumes = "application/json", produces = "application/json")
    public ResponseEntity createVehicle(@RequestBody @Valid CreateDriverRequest request, BindingResult bindingResult) {
        try {
            DriverDto result = _driverService.createDriver(request);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return new ResponseEntity<>(new BaseResponse("Thêm tài xế mới thất bại."), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/driver/get-drivers")
    public ResponseEntity getDrivers(
            @RequestParam(name = "sortBy", defaultValue = "created_by", required = false) String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = "desc", required = false) String sortOrder,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        try {
            PageDto<DriverDto> result = _driverService.getDriversByCurrentUser(sortBy, sortOrder, page, size);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return new ResponseEntity<>(new BaseResponse("Lỗi xảy ra khi tìm kiếm tài xế."), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/vehicle/update-drivers", consumes = "application/json", produces = "application/json")
    public ResponseEntity createVehicle(@RequestBody @Valid UpdateDriverVehicle request, BindingResult bindingResult) {
        try {
            VehicleDto result = _vehicleService.updateVehicleDrivers(request);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return new ResponseEntity<>(new BaseResponse("Cập nhật tài xế phương tiện thất bại."), HttpStatus.BAD_REQUEST);
        }
    }

    //endregion
}
