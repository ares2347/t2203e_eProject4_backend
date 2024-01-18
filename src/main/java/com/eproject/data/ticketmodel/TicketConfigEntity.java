package com.eproject.data.ticketmodel;

import com.eproject.data.BaseEntity;
import com.eproject.data.tripmodel.TripConfigEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "ticket_configs")
@NoArgsConstructor
@AllArgsConstructor
public class TicketConfigEntity extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "ticket_config_id", columnDefinition = "BINARY(16)", updatable = false, nullable = false)
    private UUID ticketConfigId;

    @Column(name = "ticket_type")
    private TicketTypeEnum ticketType;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "seat")
    private String seat;

    @Column(name = "coach")
    private String coach;

    @Column(name= "ticket_description")
    private String ticketDescription;

    @ManyToOne
    @JoinColumn(name = "trip_config_id")
    @JsonIgnore
    private TripConfigEntity tripConfig;

    @OneToMany(mappedBy = "ticketConfig")
    List<TicketEntity> tickets;

}
