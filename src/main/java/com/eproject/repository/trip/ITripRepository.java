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
    @Query(value = "SELECT t.trip_id, t.trip_status, t.depart_date, t.seat_remains, t.driver_email, t.driver_phone, t.driver_name," +
            "c.trip_config_id, c.depart_from, c.depart_at, c.arrive_to, c.arrive_at, c.price " +
            "FROM trip_configs c LEFT JOIN trips t ON c.trip_config_id = t.trip_config_id " +
            "WHERE c.depart_from LIKE %?1% AND c.arrive_to LIKE %?2% " +
            "AND (c.is_repeated = true or (DATE(c.depart_at) = ?3)) " +
            "AND (t.depart_date = null or DATE(t.depart_date = ?3))" +
            "AND c.is_deleted = false AND t.is_deleted = false",
            countQuery = "SELECT count(*) " +
                    "FROM trip_configs c LEFT JOIN trips t ON c.trip_config_id = t.trip_config_id " +
                    "WHERE depart_at LIKE %?1% AND arrive_to LIKE %?2% " +
                    "AND (is_repeated = true or (DATE(depart_at) = ?3)) " +
                    "AND (t.depart_date = null or DATE(t.depart_date = ?3))" +
                    "AND c.is_deleted = false AND t.is_deleted = false",
            nativeQuery = true)
    Page<TripDto> getTripsByParams(String departFrom, String arriveTo, Date departAt, Pageable pageable);
}
