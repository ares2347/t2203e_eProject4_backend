package com.data.tripmodel;

import com.data.BaseEntity;
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
@Table(name = "trips")
@NoArgsConstructor
@AllArgsConstructor
public class TripEntity extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "trip_id", columnDefinition = "BINARY(16)", updatable = false, nullable = false)
    private UUID tripId;

    @ManyToOne
    @JoinColumn(name = "trip_config_id")
    @JsonIgnore
    private TripConfigEntity tripConfig;
}
