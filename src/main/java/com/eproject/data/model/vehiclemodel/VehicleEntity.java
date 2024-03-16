package com.eproject.data.model.vehiclemodel;

import com.eproject.data.model.BaseEntity;
import com.eproject.data.model.brandmodel.BrandEntity;
import com.eproject.data.model.brandmodel.DriverEntity;
import com.eproject.data.model.tripmodel.TripEntity;
import com.eproject.data.model.tripmodel.TripStatusEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "vehicles")
@NoArgsConstructor
@AllArgsConstructor
public class VehicleEntity extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "vehicle_id", columnDefinition = "BINARY(16)", updatable = false, nullable = false)
    private UUID vehicleId;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "vehicle_type", nullable = false)
    private VehicleTypeEnum vehicleType;

    @Column(name = "vehicle_brand")
    private String vehicleBrand;

    @Column(name = "license_plate", nullable = false)
    private String licensePlate;

    @Column(name = "seat_amount", nullable = false)
    private Integer seatAmount;

    @Column(name = "current_station")
    private String currentStation;

    @Column(name = "previous_station")
    private String previousStation;

    @Column(name = "last_start_time")
    private LocalDateTime lastStartTime;

    @Column(name = "vehicle_status")
    @Enumerated(EnumType.ORDINAL)
    private TripStatusEnum vehicleStatus;

    @Column(name = "photo_url")
    private String photoUrl;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<TripEntity> trips;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<DriverEntity> drivers;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private BrandEntity brand;
}
