package com.data.ticketmodel;

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
@Table(name = "ticket_configs")
@NoArgsConstructor
@AllArgsConstructor
public class TicketConfigEntity extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "ticket_config_id", columnDefinition = "BINARY(16)", updatable = false, nullable = false)
    private UUID ticketConfigId;
}
