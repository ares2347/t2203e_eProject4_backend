package com.eproject.webapi.brandcontroller;

import com.eproject.data.tripmodel.TripConfigEntity;
import com.eproject.data.usermodel.UserEntity;
import com.eproject.data.vehiclemodel.VehicleConfigEntity;
import com.eproject.service.trip.TripService;
import com.eproject.service.user.BrandService;
import com.eproject.service.user.IBrandService;
import com.eproject.service.vehicle.VehicleService;
import com.eproject.webapi.authcontroller.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/brand")
public class BrandController {
    @Autowired
    BrandService _brandService;

    @Autowired
    private VehicleService _vehicleService;

    @Autowired
    private TripService _tripService;

    @PostMapping(path = "/vehicle/config", consumes = "application/json", produces = "application/json")
    public ResponseEntity<VehicleConfigEntity> addVehicleConfig(@RequestBody CreateVehicleConfigRequest request){
        try {
            VehicleConfigEntity result = _vehicleService.addConfig(request);
            return new ResponseEntity<VehicleConfigEntity>(result, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<VehicleConfigEntity>(new VehicleConfigEntity(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/vehicle/config/list")
    public ResponseEntity<Page<VehicleConfigEntity>> getVehicleConfigList(
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String sort,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Page<VehicleConfigEntity> result = _vehicleService.getBrandVegicleConfifgList(sortBy, sort, page, size);
            return new ResponseEntity<Page<VehicleConfigEntity>>(result, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<Page<VehicleConfigEntity>>(Page.empty(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/trip/config", consumes = "application/json", produces = "application/json")
    public ResponseEntity<TripConfigEntity> addTripConfig(@RequestBody CreateTripConfigRequest request){
        try {
            TripConfigEntity result = _tripService.addTripConfig(request);
            return new ResponseEntity<TripConfigEntity>(result, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<TripConfigEntity>(new TripConfigEntity(), HttpStatus.BAD_REQUEST);
        }
    }
}
