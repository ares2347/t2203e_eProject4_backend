package com.eproject.repository.trip;

import com.eproject.data.tripmodel.TripConfigEntity;
import com.eproject.data.tripmodel.TripEntity;
import com.eproject.repository.IBaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.sql.Time;

public interface ITripConfigRepository extends IBaseRepository<TripConfigEntity> {

    @Query(value = "SELECT c.* " +
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
    Page<TripConfigEntity> getTripConfigsByParams(String departFrom, String arriveTo, Date departAt, Pageable pageable);
}
