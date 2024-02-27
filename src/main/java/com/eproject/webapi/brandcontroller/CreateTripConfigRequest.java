package com.eproject.webapi.brandcontroller;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public class CreateTripConfigRequest {
    public String departFrom;
    public String arriveTo;
    public LocalTime departAt;
    public LocalTime arriveAt;
    public String stops;
    public UUID vehicleId;
    public boolean isRepeated;
    public BigDecimal price;
    public List<TicketConfigRequest> ticketConfigs;
}

