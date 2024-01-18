package com.eproject.repository.trip;

import com.eproject.data.tripmodel.TripConfigEntity;
import com.eproject.data.tripmodel.TripEntity;
import com.eproject.repository.IBaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.sql.Time;

public interface ITripConfigRepository extends IBaseRepository<TripConfigEntity> {
    Page<TripConfigEntity> findTripConfigEntitiesByArriveAt(Time arriveAt, Pageable pageable);
    Page<TripConfigEntity> findTripConfigEntitiesByArriveToContainsIgnoreCase(String arriveTo, Pageable pageable);
    Page<TripConfigEntity> findTripConfigEntitiesByDepartFromContainsIgnoreCase(String departFrom, Pageable pageable);
    Page<TripConfigEntity> findTripConfigEntitiesByDepartFromContainsIgnoreCaseAndArriveToContainsIgnoreCase(String departFrom,String arriveTo, Pageable pageable);
}
