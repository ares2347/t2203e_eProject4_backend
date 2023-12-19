package com.data.tripmodel;

import com.data.BaseEntity;
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
@Table(name = "trip_configs")
@NoArgsConstructor
@AllArgsConstructor
public class TripConfigEntity extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "trip_config_id", columnDefinition = "BINARY(16)", updatable = false, nullable = false)
    private UUID tripConfigId;

    @OneToMany(mappedBy = "tripConfig")
    List<TripEntity> trips;
}
