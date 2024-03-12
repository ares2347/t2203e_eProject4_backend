package com.eproject.service.trip;

import com.eproject.data.tripmodel.TripConfigEntity;
import com.eproject.data.tripmodel.TripDto;
import com.eproject.data.tripmodel.TripEntity;
import com.eproject.webapi.brandcontroller.CreateTripConfigRequest;
import org.springframework.data.domain.Page;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.UUID;

public interface ITripService {
    Page<TripEntity> getList(String sortBy, String sort, int page, int size);
    Page<TripEntity> getList(int page, int size);
    TripDto getDetail(UUID id);
    TripConfigEntity getTripConfig(UUID id);
    TripConfigEntity addTripConfig(CreateTripConfigRequest request);
    Page<TripDto> getList(String departFrom, String arriveTo, Date departAt, String sortBy, String sort, int page, int size);
    Page<TripConfigEntity> getConfigList(String departFrom, String arriveTo, Date departAt, String sortBy, String sort, int page, int size);

}
