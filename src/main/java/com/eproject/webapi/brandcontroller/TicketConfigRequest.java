package com.eproject.webapi.brandcontroller;

import com.eproject.data.ticketmodel.TicketTypeEnum;
import jakarta.persistence.Column;

import java.math.BigDecimal;

public class TicketConfigRequest {
    public TicketTypeEnum ticketType;

    public BigDecimal price;

    public String seat;

    public String coach;

    public String ticketDescription;
}
