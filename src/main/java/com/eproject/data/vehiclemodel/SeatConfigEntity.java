package com.eproject.data.vehiclemodel;

import com.eproject.data.BaseEntity;
import com.eproject.data.usermodel.BrandEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "seat_configs", schema = "eproject")
@NoArgsConstructor
@AllArgsConstructor
public class SeatConfigEntity extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "seat_config_id", columnDefinition = "BINARY(16)", updatable = false, nullable = false)
    private UUID seatConfigId;

    @ManyToOne
    @JoinColumn(name = "vehicle_config_id")
    @JsonIgnore
    private VehicleConfigEntity vehicleConfiguration;

    @Column(name = "seat")
    private String seat;

    @Column(name = "coach")
    private String coach;

    public SeatConfigEntity(VehicleConfigEntity vehicleConfig, String seat, String coach) {
        this.vehicleConfiguration = vehicleConfig;
        this.seat = seat;
        this.coach = coach;
    }

    public SeatConfigEntity(String seat, String coach) {
        this.seat = seat;
        this.coach = coach;
    }
}
