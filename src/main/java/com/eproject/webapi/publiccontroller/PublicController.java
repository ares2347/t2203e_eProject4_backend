package com.eproject.webapi.publiccontroller;

import com.eproject.data.dto.PageDto;
import com.eproject.data.dto.trip.RouteDto;
import com.eproject.data.dto.trip.TripDto;
import com.eproject.data.model.tripmodel.RouteEntity;
import com.eproject.service.trip.TripService;
import com.eproject.webapi.BaseResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/public")
public class PublicController {
    @Autowired
    private TripService _tripService;

    @Autowired
    private ModelMapper _mapper;

    @GetMapping(path = "/trip/get-trips")
    public ResponseEntity getRoutes(
            @RequestParam(name = "startDate") LocalDate startDate,
            @RequestParam(name = "startCity") String startCity,
            @RequestParam(name = "endCity") String endCity,
            @RequestParam(name = "vehicleType", defaultValue = "", required = false) String vehicleType,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        try {
            PageDto<TripDto> result = _tripService.getTripsByLocationAndDate(startDate, startCity, endCity, vehicleType, page, size);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return new ResponseEntity<>(new BaseResponse("Lỗi xảy ra khi tìm kiếm chuyến xe."), HttpStatus.BAD_REQUEST);
        }
    }
}
