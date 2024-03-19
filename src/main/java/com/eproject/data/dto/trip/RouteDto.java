package com.eproject.data.dto.trip;

import com.eproject.data.model.tripmodel.RouteEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalTime;
import java.util.UUID;

public class RouteDto {
    public UUID routeId;
    public String startCity;
    public String startStation;
    public String endCity;
    public String endStation;
    @JsonFormat(pattern = "HH:mm:ss")
    public LocalTime routeDuration;
    @JsonFormat(pattern = "HH:mm:ss")
    public LocalTime earliestStartTimeFromStart;

    @JsonFormat(pattern = "HH:mm:ss")
    public LocalTime latestStartTimeFromStart;

    @JsonFormat(pattern = "HH:mm:ss")
    public LocalTime earliestStartTimeFromEnd;

    @JsonFormat(pattern = "HH:mm:ss")
    public LocalTime latestStartTimeFromEnd;
    @JsonFormat(pattern = "HH:mm:ss")
    public LocalTime gapDurationBetweenTrip;
    @JsonFormat(pattern = "HH:mm:ss")
    public LocalTime gapDurationBetweenRoute;

    public String stationsMapping;

    public String vehicleType;

    public Integer seatAmount;

    public RouteDto(RouteEntity entity) {
        this.routeId = entity.getRouteId();
        this.startCity = entity.getStartCity();
        this.startStation = entity.getStartStation();
        this.endCity = entity.getEndCity();
        this.endStation = entity.getEndStation();
        this.routeDuration = entity.getRouteDuration();
        this.earliestStartTimeFromStart = entity.getEarliestStartTimeFromStart();
        this.latestStartTimeFromStart = entity.getLatestStartTimeFromStart();
        this.earliestStartTimeFromEnd = entity.getEarliestStartTimeFromEnd();
        this.latestStartTimeFromEnd = entity.getLatestStartTimeFromEnd();
        this.gapDurationBetweenTrip = entity.getGapDurationBetweenTrip();
        this.gapDurationBetweenRoute = entity.getGapDurationBetweenRoute();
        this.stationsMapping = entity.getStationsMapping();
        this.vehicleType = entity.getVehicleType();
        this.seatAmount = entity.getSeatAmount();
    }
}
