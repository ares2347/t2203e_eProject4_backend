package com.eproject.service.trip;

import com.eproject.data.tripmodel.TripConfigEntity;
import com.eproject.data.tripmodel.TripEntity;
import com.eproject.webapi.brandcontroller.CreateTripConfigRequest;
import org.springframework.data.domain.Page;

import java.sql.Time;
import java.util.UUID;

public interface ITripService {
    Page<TripEntity> getList(String sortBy, String sort, int page, int size);
    Page<TripEntity> getList(int page, int size);
    TripEntity getDetail(UUID id);
    TripConfigEntity getTripConfig(UUID id);
    Page<TripConfigEntity> getConfigList(String sortBy, String sort, int page, int size);
    Page<TripConfigEntity> getConfigList(int page, int size);
    Page<TripConfigEntity> getConfigList(String sortBy, String sort, int page, int size, String departFrom, String arriveTo, Time departAt, Time arriveAt);
    TripConfigEntity addTripConfig(CreateTripConfigRequest request);

}
