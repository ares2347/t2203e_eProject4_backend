package com.data.vehiclemodel;

import com.data.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "vehicle_configs")
@NoArgsConstructor
@AllArgsConstructor
public class VehicleConfigEntity extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "vehicle_config_id", columnDefinition = "BINARY(16)", updatable = false, nullable = false)
    private UUID vehicleConfigId;
}
