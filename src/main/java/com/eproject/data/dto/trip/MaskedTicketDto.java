package com.eproject.data.dto.trip;

import com.eproject.data.model.ticketmodel.TicketEntity;
import jakarta.persistence.Column;

public class MaskedTicketDto {
    public String pickupPoint;
    public String dropoffPoint;
    public String seatNumber;

    public MaskedTicketDto(TicketEntity entity) {
        this.pickupPoint = entity.getPickupPoint();
        this.dropoffPoint = entity.getDropoffPoint();
        this.seatNumber = entity.getSeatNumber();
    }
}
