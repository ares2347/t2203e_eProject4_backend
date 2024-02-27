package com.eproject.repository.trip;

import com.eproject.data.tripmodel.TripConfigEntity;
import com.eproject.data.tripmodel.TripDto;
import com.eproject.data.tripmodel.TripEntity;
import com.eproject.data.usermodel.UserEntity;
import com.eproject.repository.IBaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;

public interface ITripRepository extends IBaseRepository<TripEntity> {
    @Query(value = "SELECT " +
            "t.trip_id as tripId, " +
            "t.trip_status as tripStatus, " +
            "t.depart_date as departDate, " +
            "t.seat_remains as seatRemains, " +
            "t.driver_email as driverEmail, " +
            "t.driver_phone as driverPhone, " +
            "t.driver_name as driverName," +
            "c.trip_config_id as tripConfigId, " +
            "c.depart_from as departFrom, " +
            "c.depart_at as departAt, " +
            "c.arrive_to as arriveTo, " +
            "c.arrive_at as arriveAt, " +
            "c.price as price " +
            "FROM trip_configs c LEFT JOIN trips t ON c.trip_config_id = t.trip_config_id " +
            "WHERE c.depart_from LIKE %?1% AND c.arrive_to LIKE %?2% " +
            "AND (c.is_repeated = true or (DATE(c.depart_at) = ?3)) " +
            "AND (t.depart_date is null or DATE(t.depart_date = ?3))" +
            "AND (c.is_deleted = false or c.is_deleted is null) AND (t.is_deleted = false or t.is_deleted is null)",
            countQuery = "SELECT count(*) " +
                    "FROM trip_configs c LEFT JOIN trips t ON c.trip_config_id = t.trip_config_id " +
                    "WHERE depart_at LIKE %?1% AND arrive_to LIKE %?2% " +
                    "AND (is_repeated = true or (DATE(depart_at) = ?3)) " +
                    "AND (t.depart_date is null or DATE(t.depart_date = ?3))" +
                    "AND (c.is_deleted = false or c.is_deleted is null) AND (t.is_deleted = false or t.is_deleted is null)",
            nativeQuery = true)
    Page<TripDto> getTripsByParams(String departFrom, String arriveTo, Date departAt, Pageable pageable);
}
