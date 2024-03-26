package com.eproject.data.model.tripmodel;

import com.eproject.data.model.BaseEntity;
import com.eproject.data.model.brandmodel.BrandEntity;
import com.eproject.webapi.brandcontroller.CreateRouteRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "routes")
@NoArgsConstructor
@AllArgsConstructor
public class RouteEntity extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "route_id", columnDefinition = "BINARY(16)", updatable = false, nullable = false)
    private UUID routeId;

    @Column(name = "start_city", nullable = false)
    private String startCity;

    @Column(name = "start_station", nullable = false)
    private String startStation;

    @Column(name = "end_city", nullable = false)
    private String endCity;

    @Column(name = "end_station", nullable = false)
    private String endStation;

    @Column(name = "route_duration", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime routeDuration;

    @Column(name = "earliest_start_time_from_start", nullable = false)
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime earliestStartTimeFromStart;

    @Column(name = "latest_start_time_from_start", nullable = false)
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime latestStartTimeFromStart;

    @Column(name = "earliest_start_time_from_end", nullable = false)
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime earliestStartTimeFromEnd;

    @Column(name = "latest_start_time_from_end", nullable = false)
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime latestStartTimeFromEnd;

    @Column(name = "gap_duration_between_trip", nullable = false)
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime gapDurationBetweenTrip;

    @Column(name = "gap_duration_between_route", nullable = false)
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime gapDurationBetweenRoute;

    @Column(name = "stations_mapping", length = 10000000, nullable = false)
    private String stationsMapping;

    @Column(name = "vehicle_type", nullable = false)
    private String vehicleType;

    @Column(name = "seat_amount", nullable = false)
    private Integer seatAmount;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private BrandEntity brand;

    public RouteEntity(CreateRouteRequest request) {
        this.startCity = request.startCity;
        this.startStation = request.startStation;
        this.endCity = request.endCity;
        this.endStation = request.endStation;
        this.routeDuration = request.routeDuration;
        this.earliestStartTimeFromStart = request.earliestStartTimeFromStart;
        this.latestStartTimeFromStart = request.latestStartTimeFromStart;
        this.earliestStartTimeFromEnd = request.earliestStartTimeFromEnd;
        this.latestStartTimeFromEnd = request.latestStartTimeFromEnd;
        this.gapDurationBetweenTrip = request.gapDurationBetweenTrip;
        this.gapDurationBetweenRoute = request.gapDurationBetweenRoute;
        this.stationsMapping = request.stationsMapping;
        this.vehicleType = request.vehicleType;
        this.seatAmount = request.seatAmount;
    }
}
