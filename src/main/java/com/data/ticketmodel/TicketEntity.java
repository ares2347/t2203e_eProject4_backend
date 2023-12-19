package com.data.ticketmodel;

import com.data.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

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
}
