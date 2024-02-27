package com.eproject.data.tripmodel;

import com.eproject.data.BaseEntity;
import com.eproject.data.ticketmodel.TicketConfigEntity;
import com.eproject.data.vehiclemodel.VehicleConfigEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "trip_configs", schema = "eproject")
@NoArgsConstructor
@AllArgsConstructor
public class TripConfigEntity extends BaseEntity {
    public TripConfigEntity(String departFrom, LocalTime departAt, String arriveTo, LocalTime arriveAt, String stops, VehicleConfigEntity vehicleConfig, boolean isRepeated, BigDecimal price, List<TicketConfigEntity> ticketConfigs) {
        this.departFrom = departFrom;
        this.departAt = departAt;
        this.arriveTo = arriveTo;
        this.arriveAt = arriveAt;
        this.stops = stops;
        this.vehicleConfig = vehicleConfig;
        this.isRepeated = isRepeated;
        this.price = price;
        this.vehicleConfig.getTripConfigs().add(this);
        this.vehicleConfig.setTripConfigs(this.getVehicleConfig().getTripConfigs());
        this.ticketConfigs = ticketConfigs;
    }

    @Id
    @GeneratedValue
    @Column(name = "trip_config_id", columnDefinition = "BINARY(16)", updatable = false, nullable = false)
    private UUID tripConfigId;

    @Column(name = "depart_from")
    @JsonFormat(pattern = "HH:mm:ss")
    private String departFrom;

    @Column(name = "depart_at")
    private LocalTime departAt;

    @Column(name = "arrive_to")
    private String arriveTo;

    @Column(name = "arrive_at")
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime arriveAt;

    @Column(name = "stops")
    private String stops;

    @Column(name = "is_repeated")
    private boolean isRepeated;

    @Column(name = "price")
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "vehicle_config_id")
    @JsonIgnore
    private VehicleConfigEntity vehicleConfig;

    @OneToMany(mappedBy = "tripConfig")
    private List<TripEntity> trips;

    @OneToMany(mappedBy = "tripConfig")
    private List<TicketConfigEntity> ticketConfigs;
}
