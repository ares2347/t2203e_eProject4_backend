package com.eproject.data.ticketmodel;

import com.eproject.data.BaseEntity;
import com.eproject.data.paymentmodel.PaymentEntity;
import com.eproject.data.tripmodel.TripEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "tickets")
@NoArgsConstructor
@AllArgsConstructor
public class TicketEntity extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "ticket_id", columnDefinition = "BINARY(16)", updatable = false, nullable = false)
    private UUID ticketId;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_dob")
    private Date customerDob;

    @Column(name = "customer_ic")
    private String customerIc;

    @Column(name = "customer_email")
    private String customerEmail;

    @Column(name = "customer_phone")
    private String customerPhone;

    @Column(name = "total")
    private BigDecimal total;

    @ManyToOne
    @JoinColumn(name = "trip_id")
    @JsonIgnore
    private TripEntity trip;

    @ManyToOne
    @JoinColumn(name = "ticket_config_id")
    @JsonIgnore
    private TicketConfigEntity ticketConfig;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id", referencedColumnName = "payment_id")
    private PaymentEntity payment;

}
