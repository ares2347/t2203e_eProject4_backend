package com.eproject.data.tripmodel;

import com.eproject.data.ticketmodel.TicketConfigEntity;

import java.sql.Time;
import java.util.List;
import java.util.UUID;

public class TripConfigDto {
    private UUID tripConfigId;

    private String departFrom;

    private Time departAt;

    private String arriveTo;

    private Time arriveAt;

    private String stops;

    List<TicketConfigEntity> ticketConfigs;
}
