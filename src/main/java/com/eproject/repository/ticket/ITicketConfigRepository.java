package com.eproject.repository.ticket;

import com.eproject.data.ticketmodel.TicketConfigEntity;
import com.eproject.data.vehiclemodel.VehicleConfigEntity;
import com.eproject.repository.IBaseRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ITicketConfigRepository extends IBaseRepository<TicketConfigEntity> {
}
