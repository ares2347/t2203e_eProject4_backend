package com.eproject.data.tripmodel;

import com.eproject.data.BaseEntity;
import com.eproject.data.ticketmodel.TicketEntity;
import com.eproject.data.vehiclemodel.VehicleEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "trips", schema = "eproject")
@NoArgsConstructor
@AllArgsConstructor
public class TripEntity extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "trip_id", columnDefinition = "BINARY(16)", updatable = false, nullable = false)
    private UUID tripId;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "trip_status")
    private TripStatusEnum tripStatus;

    @Column(name = "driver_email")
    private String driverEmail;

    @Column(name = "driver_phone")
    private String driverPhone;

    @Column(name = "driver_name")
    private String driverName;

    @Column(name = "depart_date")
    private Date departDate;

    @Column(name = "seat_remains")
    private int seatRemains;

    @ManyToOne
    @JoinColumn(name = "trip_config_id")
    @JsonIgnore
    private TripConfigEntity tripConfig;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    @JsonIgnore
    private VehicleEntity vehicle;

    @OneToMany(mappedBy = "trip")
    List<TicketEntity> tickets;


    public TripEntity(TripConfigEntity tripConfig) {
        this.tripConfig = tripConfig;
    }
}
