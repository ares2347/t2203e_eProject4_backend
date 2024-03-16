package com.eproject.data.dto.trip;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalTime;
import java.util.UUID;

public class RouteDto {
    private UUID routeId;
    private String startCity;
    private String startStation;
    private String endCity;
    private String endStation;

    private Integer routeDuration;
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime earliestStartTimeFromStart;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime latestStartTimeFromStart;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime earliestStartTimeFromEnd;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime latestStartTimeFromEnd;

    private Integer gapDurationBetweenTrip;

    private Integer gapDurationBetweenRoute;

    private String stationsMapping;

    private String vehicleType;

    private Integer seatAmount;
}
