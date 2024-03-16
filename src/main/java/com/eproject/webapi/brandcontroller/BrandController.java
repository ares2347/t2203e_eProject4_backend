package com.eproject.webapi.brandcontroller;

import com.eproject.data.dto.PageDto;
import com.eproject.data.dto.trip.RouteDto;
import com.eproject.data.model.tripmodel.RouteEntity;
import com.eproject.data.model.usermodel.RoleEntity;
import com.eproject.data.model.usermodel.UserEntity;
import com.eproject.data.model.usermodel.UserRolesEnum;
import com.eproject.service.trip.TripService;
import com.eproject.service.user.BrandService;
import com.eproject.service.vehicle.VehicleService;
import com.eproject.webapi.BaseResponse;
import com.eproject.webapi.authcontroller.AuthResponse;
import com.eproject.webapi.authcontroller.RegisterRequest;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/brand")
public class BrandController {
    @Autowired
    private TripService _tripService;

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

}
