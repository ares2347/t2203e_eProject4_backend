package com.eproject.webapi.publiccontroller;

import com.eproject.data.tripmodel.TripConfigEntity;
import com.eproject.data.tripmodel.TripEntity;
import com.eproject.service.trip.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.time.OffsetDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/api/public/trip")
public class PublicTripController {
    @Autowired
    private TripService _tripService;

    @GetMapping("/list")
    public ResponseEntity<Page<TripEntity>> getTripList(
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String sort,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Page<TripEntity> result = _tripService.getList(sortBy, sort, page, size);
            return new ResponseEntity<Page<TripEntity>>(result, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<Page<TripEntity>>(Page.empty(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<TripEntity> getTripDetail(@PathVariable UUID id) {
        try {
            TripEntity result = _tripService.getDetail(id);
            return new ResponseEntity<TripEntity>(result, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<TripEntity>(new TripEntity(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/config/list")
    public ResponseEntity<Page<TripConfigEntity>> getConfigList(
            @RequestParam(defaultValue = "departAt") String sortBy,
            @RequestParam(defaultValue = "desc") String sort,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "", required = false) String departFrom,
            @RequestParam(defaultValue = "", required = false) String arriveTo,
            @RequestParam(required = false) Time departAt,
            @RequestParam(required = false) Time arriveAt) {
        try {
            departAt = departAt == null ? new Time(OffsetDateTime.now().toEpochSecond()) : departAt;
            Page<TripConfigEntity> result = _tripService.getConfigList(sortBy, sort, page, size);
            return new ResponseEntity<Page<TripConfigEntity>>(result, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<Page<TripConfigEntity>>(Page.empty(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/config/detail/{id}")
    public ResponseEntity<TripConfigEntity> getConfigDetail(@PathVariable UUID id) {
        try {
            TripConfigEntity result = _tripService.getTripConfig(id);
            return new ResponseEntity<TripConfigEntity>(result, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<TripConfigEntity>(new TripConfigEntity(), HttpStatus.BAD_REQUEST);
        }
    }
}
