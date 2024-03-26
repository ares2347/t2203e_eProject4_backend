package com.eproject.data.model.tripmodel;

import com.eproject.data.model.BaseEntity;
import com.eproject.data.model.brandmodel.BrandEntity;
import com.eproject.data.model.ticketmodel.TicketEntity;
import com.eproject.data.model.vehiclemodel.VehicleEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "trips")
@NoArgsConstructor
@AllArgsConstructor
public class TripEntity extends BaseEntity {
    public TripEntity(TripStatusEnum tripStatus, String startCity, String startStation, String endCity, String endStation, LocalTime startTime, LocalDate startDate, LocalTime routeDuration, String stationsMapping, String vehicleType, Integer seatAmount, BrandEntity brand, VehicleEntity vehicle) {
        this.tripStatus = tripStatus;
        this.startCity = startCity;
        this.startStation = startStation;
        this.endCity = endCity;
        this.endStation = endStation;
        this.startTime = startTime;
        this.startDate = startDate;
        this.routeDuration = routeDuration;
        this.stationsMapping = stationsMapping;
        this.vehicleType = vehicleType;
        this.seatAmount = seatAmount;
        this.brand = brand;
        this.vehicle = vehicle;
    }

    @Id
    @GeneratedValue
    @Column(name = "trip_id", columnDefinition = "BINARY(16)", updatable = false, nullable = false)
    private UUID tripId;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "trip_status")
    private TripStatusEnum tripStatus;

    @Column(name = "start_city", nullable = false)
    private String startCity;

    @Column(name = "start_station", nullable = false)
    private String startStation;

    @Column(name = "end_city", nullable = false)
    private String endCity;

    @Column(name = "end_station", nullable = false)
    private String endStation;

    @Column(name = "start_time", nullable = false)
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime startTime;

    @Column(name = "start_date", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private LocalDate startDate;

    @Column(name = "route_duration", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime routeDuration;

    @Column(name = "stations_mapping",length = 10000000, nullable = false)
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vehicle_id")
    @JsonIgnore
    private VehicleEntity vehicle;

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<TicketEntity> tickets;

}
