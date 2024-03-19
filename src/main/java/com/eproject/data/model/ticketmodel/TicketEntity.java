package com.eproject.data.model.ticketmodel;

import com.eproject.data.model.BaseEntity;
import com.eproject.data.model.paymentmodel.PaymentEntity;
import com.eproject.data.model.tripmodel.TripEntity;
import com.eproject.webapi.usercontroller.BookTicketRequestDetail;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
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

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Column(name = "customer_dob")
    private Date customerDob;

    @Column(name = "customer_ic")
    private String customerIc;

    @Column(name = "customer_email", nullable = false)
    private String customerEmail;

    @Column(name = "customer_phone", nullable = false)
    private String customerPhone;

    @Column(name = "pickup_point", nullable = false)
    private String pickupPoint;

    @Column(name = "dropoff_point", nullable = false)
    private String dropoffPoint;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "start_time", nullable = false)
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime startTime;

    @Column(name = "start_date", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private LocalDate startDate;

    @Column(name = "seat_number", nullable = false)
    private String seatNumber;

    @ManyToOne
    @JoinColumn(name = "trip_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private TripEntity trip;

    @OneToOne(mappedBy = "ticket")
    private PaymentEntity payment;

    public TicketEntity(BookTicketRequestDetail request) {
        this.customerName = request.customerName;
        this.customerDob = request.customerDob;
        this.customerIc = request.customerIc;
        this.customerEmail = request.customerEmail;
        this.customerPhone = request.customerPhone;
        this.pickupPoint = request.pickupPoint;
        this.dropoffPoint = request.dropoffPoint;
        this.price = request.price;
        this.seatNumber = request.seatNumber;
    }
}
