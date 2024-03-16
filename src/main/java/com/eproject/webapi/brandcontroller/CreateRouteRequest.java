package com.eproject.webapi.brandcontroller;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;

import java.time.LocalTime;

public class CreateRouteRequest {
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
}
