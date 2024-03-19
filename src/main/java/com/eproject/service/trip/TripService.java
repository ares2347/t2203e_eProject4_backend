package com.eproject.service.trip;

import com.eproject.data.dto.PageDto;
import com.eproject.data.dto.trip.RouteDto;
import com.eproject.data.dto.trip.TripDto;
import com.eproject.data.model.brandmodel.BrandEntity;
import com.eproject.data.model.tripmodel.RouteEntity;
import com.eproject.data.model.tripmodel.TripEntity;
import com.eproject.data.model.tripmodel.TripStatusEnum;
import com.eproject.data.model.usermodel.UserEntity;
import com.eproject.data.model.vehiclemodel.VehicleEntity;
import com.eproject.repository.RouteRepository;
import com.eproject.repository.TripRepository;
import com.eproject.repository.UserRepository;
import com.eproject.repository.VehicleRepository;
import com.eproject.service.auth.JwtService;
import com.eproject.webapi.brandcontroller.CreateRouteRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

@Service
public class TripService implements ITripService {
    @Autowired
    private RouteRepository _routeRepository;
    @Autowired
    private TripRepository _tripRepository;
    @Autowired
    private VehicleRepository _vehicleRepository;
    @Autowired
    private UserRepository _userRepository;
    @Autowired
    private JwtService _jwtService;

    @Override
    public RouteEntity createNewRoute(CreateRouteRequest request) {
        try {
            UserEntity user = _jwtService.getCurrentUser();
            Optional<UserEntity> userEntity = _userRepository.findById(user.getUserId());
            if (userEntity.isPresent()) {
                BrandEntity brand = userEntity.get().getBrand();
                RouteEntity route = new RouteEntity(request);
                route.setBrand(brand);
                RouteEntity res = _routeRepository.save(route);
                this.addTripToRoute(res);
                return res;
            } else {
                return null;
            }
        } catch (Exception ex) {
            return null;
        }
    }

    public List<TripEntity> addTripToRoute(RouteEntity route) {
        List<TripEntity> trips = new ArrayList<>();
        List<LocalDate> remainsDaysOfMonth = LocalDate.now()
                .datesUntil(LocalDate.now().with(TemporalAdjusters.lastDayOfMonth()).plusDays(1))
                .toList();
        //Get vehicle and add routes??????
        int totalRouteDuration =
                (route.getRouteDuration().getHour() * 60 * 60 + route.getRouteDuration().getMinute() * 60 + route.getRouteDuration().getSecond()) +
                        (route.getGapDurationBetweenTrip().getHour() * 60 * 60 + route.getGapDurationBetweenTrip().getMinute() * 60 + route.getGapDurationBetweenTrip().getSecond());
        int gapDurationBetweenRoute = route.getGapDurationBetweenRoute().getHour() * 60 * 60 + route.getGapDurationBetweenRoute().getMinute() * 60 + route.getGapDurationBetweenRoute().getSecond();
        int numberOfVehicleNeededForOneCycle = (BigDecimal.valueOf(totalRouteDuration).divide(BigDecimal.valueOf(gapDurationBetweenRoute)).setScale(0, RoundingMode.FLOOR)).intValueExact();

        LinkedList<VehicleEntity> vehiclesInStart = new LinkedList<>(_vehicleRepository.findAllByCurrentStationAndPreviousStationNull(route.getStartStation()));
        LinkedList<VehicleEntity> vehiclesInEnd = new LinkedList<>(_vehicleRepository.findAllByCurrentStationAndPreviousStationNull(route.getEndStation()));

        if ((long) vehiclesInStart.size() >= numberOfVehicleNeededForOneCycle && (long) vehiclesInEnd.size() >= numberOfVehicleNeededForOneCycle) {
            for (LocalDate day : remainsDaysOfMonth) {
                LocalTime startTimeFromStart = route.getEarliestStartTimeFromStart();
                LocalTime latestStartTimeFromStart = route.getLatestStartTimeFromStart();
                do {
                    if (day.isAfter(LocalDate.now()) || (day.isEqual(LocalDate.now()) && startTimeFromStart.isAfter(LocalTime.now()))) {
                        VehicleEntity startVehicle = vehiclesInStart.removeFirst();
                        vehiclesInEnd.addLast(startVehicle);
                        trips.add(new TripEntity(
                                TripStatusEnum.WAITING,
                                route.getStartCity(),
                                route.getStartStation(),
                                route.getEndCity(),
                                route.getEndStation(),
                                startTimeFromStart,
                                day,
                                route.getRouteDuration(),
                                route.getStationsMapping(),
                                route.getVehicleType(),
                                route.getSeatAmount(),
                                route.getBrand(),
                                startVehicle
                        ));
                    }
                    startTimeFromStart = startTimeFromStart
                            .plusHours(route.getGapDurationBetweenRoute().getHour())
                            .plusMinutes(route.getGapDurationBetweenRoute().getMinute())
                            .plusSeconds(route.getGapDurationBetweenRoute().getSecond());
                } while (!startTimeFromStart.isAfter(latestStartTimeFromStart));

                LocalTime startTimeFromEnd = route.getEarliestStartTimeFromEnd();
                LocalTime latestStartTimeFromEnd = route.getLatestStartTimeFromEnd();
                do {
                    if (day.isAfter(LocalDate.now()) || (day.isEqual(LocalDate.now()) && startTimeFromEnd.isAfter(LocalTime.now()))) {
                        VehicleEntity endVehicle = vehiclesInEnd.removeFirst();
                        vehiclesInStart.addLast(endVehicle);
                        trips.add(new TripEntity(
                                TripStatusEnum.WAITING,
                                route.getStartCity(),
                                route.getStartStation(),
                                route.getEndCity(),
                                route.getEndStation(),
                                startTimeFromEnd,
                                day,
                                route.getRouteDuration(),
                                route.getStationsMapping(),
                                route.getVehicleType(),
                                route.getSeatAmount(),
                                route.getBrand(),
                                endVehicle
                        ));
                    }
                    startTimeFromEnd = startTimeFromEnd
                            .plusHours(route.getGapDurationBetweenRoute().getHour())
                            .plusMinutes(route.getGapDurationBetweenRoute().getMinute())
                            .plusSeconds(route.getGapDurationBetweenRoute().getSecond());
                } while (!startTimeFromEnd.isAfter(latestStartTimeFromEnd));
            }
        }
        return _tripRepository.saveAll(trips);
    }

    @Override
    public PageDto<RouteDto> getRoutesByCurrentUser(int page, int size) {
        try {
            UserEntity user = _jwtService.getCurrentUser();
            Optional<UserEntity> userEntity = _userRepository.findById(user.getUserId());
            if (userEntity.isPresent()) {
                BrandEntity brand = userEntity.get().getBrand();
                Pageable pagination = PageRequest.of(page, size);
                Page<RouteEntity> queryResult = _routeRepository.findAllByBrand(brand, pagination);
                return new PageDto<RouteDto>(
                        queryResult.get().map(x -> new RouteDto(x)).toList(),
                        queryResult.getNumber(),
                        queryResult.getSize(),
                        queryResult.getTotalPages(),
                        queryResult.getNumberOfElements());
            } else {
                return PageDto.empty();
            }
        } catch (Exception ex) {
            return PageDto.empty();
        }
    }

    @Override
    public PageDto<TripDto> getTripsByCurrentUser(int page, int size) {
        try {
            UserEntity user = _jwtService.getCurrentUser();
            Optional<UserEntity> userEntity = _userRepository.findById(user.getUserId());
            if (userEntity.isPresent()) {
                BrandEntity brand = userEntity.get().getBrand();
                Pageable pagination = PageRequest.of(page, size);
                Page<TripEntity> queryResult = _tripRepository.findAllByBrand(brand, pagination);
                return new PageDto<TripDto>(
                        queryResult.get().map(x -> new TripDto(x)).toList(),
                        queryResult.getNumber(),
                        queryResult.getSize(),
                        queryResult.getTotalPages(),
                        queryResult.getNumberOfElements());
            } else {
                return PageDto.empty();
            }
        } catch (Exception ex) {
            return PageDto.empty();
        }
    }

    @Override
    public PageDto<TripDto> getTripsByLocationAndDate(LocalDate startDate, String startCity, String endCity, String vehicleType, int page, int size) {
        try {
            Pageable pagination = PageRequest.of(page, size);
            Page<TripEntity> queryResult = _tripRepository.findAllByStartDateAndStartCityAndEndCityAndVehicleTypeContains(
                    startDate, startCity, endCity, vehicleType, pagination);
            return new PageDto<TripDto>(
                    queryResult.get().map(x -> new TripDto(x)).toList(),
                    queryResult.getNumber(),
                    queryResult.getSize(),
                    queryResult.getTotalPages(),
                    queryResult.getNumberOfElements());
        } catch (Exception ex) {
            return PageDto.empty();
        }
    }

    @Scheduled(cron = "0 0 0 1 * *")
    public void generateMonthlyTrips() {
        try{
            List<RouteEntity> routeEntities = _routeRepository.findAll();
            for (RouteEntity route : routeEntities) {
                addTripToRoute(route);
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
