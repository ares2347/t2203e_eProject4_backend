package com.eproject.data.paymentmodel;

import com.eproject.data.BaseEntity;
import com.eproject.data.ticketmodel.TicketEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "payments", schema = "eproject")
@NoArgsConstructor
@AllArgsConstructor
public class PaymentEntity extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "payment_id", columnDefinition = "BINARY(16)", updatable = false, nullable = false)
    private UUID paymentId;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "payment_status")
    private PaymentStatusEnum paymentStatus;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "payment_method")
    private PaymentMethodEnum paymentMethod;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @OneToOne(mappedBy = "payment")
    private TicketEntity ticket;

}
