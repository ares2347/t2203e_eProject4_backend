package com.eproject.service.trip;

import com.eproject.data.dto.PageDto;
import com.eproject.data.dto.trip.RouteDto;
import com.eproject.data.dto.trip.TripDto;
import com.eproject.data.model.brandmodel.BrandEntity;
import com.eproject.data.model.tripmodel.RouteEntity;
import com.eproject.data.model.tripmodel.TripEntity;
import com.eproject.webapi.brandcontroller.CreateRouteRequest;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public interface ITripService {
    RouteEntity createNewRoute(CreateRouteRequest request);

    PageDto<RouteDto> getRoutesByCurrentUser(int page, int size);
    PageDto<TripDto> getTripsByCurrentUser(int page, int size);

    PageDto<TripDto> getTripsByLocationAndDate(LocalDate startDate, String startCity, String endCity, String vehicleType, int page, int size);
}
