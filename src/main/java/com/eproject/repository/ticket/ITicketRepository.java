package com.eproject.repository.ticket;

import com.eproject.data.ticketmodel.TicketEntity;
import com.eproject.data.vehiclemodel.VehicleConfigEntity;
import com.eproject.repository.IBaseRepository;

import java.util.List;
import java.util.UUID;

public interface ITicketRepository extends IBaseRepository<TicketEntity> {
    TicketEntity getFirstByTicketIdAndCreatedBy(UUID ticketId, UUID createdBy);
    List<TicketEntity> getAllByCreatedBy (UUID createdBy);
}
