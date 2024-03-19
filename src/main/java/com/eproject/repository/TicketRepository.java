package com.eproject.repository;

import com.eproject.data.model.ticketmodel.TicketEntity;
import com.eproject.data.model.usermodel.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.UUID;

public interface TicketRepository extends JpaRepository<TicketEntity, UUID> {
    Page<TicketEntity> findAllByStartDateAndDropoffPointAndPickupPointAndCreatedBy(LocalDate startDate, String dropoffPoint, String pickupPoint, UserEntity createdBy, Pageable pageable);
}
