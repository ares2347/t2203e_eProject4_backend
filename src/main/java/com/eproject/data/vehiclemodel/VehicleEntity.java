package com.eproject.data.vehiclemodel;

import com.eproject.data.BaseEntity;
import com.eproject.data.tripmodel.TripEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "vehicles", schema = "eproject")
@NoArgsConstructor
@AllArgsConstructor
public class VehicleEntity extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "vehicle_id", columnDefinition = "BINARY(16)", updatable = false, nullable = false)
    private UUID vehicleId;

    @Column(name = "license_plate")
    private String licensePlate;

    @OneToMany(mappedBy = "vehicle")
    List<TripEntity> trips;

    @ManyToOne
    @JoinColumn(name = "vehicle_config_id")
    @JsonIgnore
    private VehicleConfigEntity vehicleConfig;
}
