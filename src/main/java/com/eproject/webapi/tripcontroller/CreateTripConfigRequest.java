package com.eproject.webapi.tripcontroller;

import java.sql.Time;
import java.util.List;
import java.util.UUID;

public class CreateTripConfigRequest {
    public String departFrom;
    public String arriveTo;
    public Time departAt;
    public Time arriveAt;
    public String stops;
    public UUID vehicleId;
}
