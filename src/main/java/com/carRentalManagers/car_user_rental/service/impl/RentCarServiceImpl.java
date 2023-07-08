package com.carRentalManagers.car_user_rental.service.impl;
import com.carRentalManagers.car_user_rental.converter.RentCarConverter;
import com.carRentalManagers.car_user_rental.dto.rentcar.RentCarPeriodUpdateRequest;
import com.carRentalManagers.car_user_rental.dto.rentcar.RentCarRequest;
import com.carRentalManagers.car_user_rental.dto.rentcar.RentCarResponse;
import com.carRentalManagers.car_user_rental.dto.rentcar.RentCarUpdateRequest;
import com.carRentalManagers.car_user_rental.entity.Car;
import com.carRentalManagers.car_user_rental.entity.RentCar;
import com.carRentalManagers.car_user_rental.exeption.CarNotFoundException;
import com.carRentalManagers.car_user_rental.exeption.RentCarNotFoundException;
import com.carRentalManagers.car_user_rental.repository.CarRepository;
import com.carRentalManagers.car_user_rental.repository.RentCarRepository;
import com.carRentalManagers.car_user_rental.service.RentCarService;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import java.util.stream.Collectors;


@Service
public class RentCarServiceImpl implements RentCarService {

    private final RentCarConverter rentCarConverter;
    private final RentCarRepository rentCarRepository;
    private final CarRepository carRepository;


    public RentCarServiceImpl(RentCarConverter rentCarConverter, RentCarRepository rentCarRepository, CarRepository carRepository) {
        this.rentCarConverter = rentCarConverter;
        this.rentCarRepository = rentCarRepository;
        this.carRepository = carRepository;
    }

    @Override
    public List<RentCarResponse> getAllRentCar() {
        List<RentCar> rentCars = rentCarRepository.findAll();
        List<RentCarResponse> rentCarResponses = new ArrayList<>();
        for(RentCar rentCar : rentCars){
            RentCarResponse response = rentCarConverter.toRentCarResponse(rentCar);
            rentCarResponses.add(response);
        }
        return rentCarResponses;
    }

    @Override
    public RentCarResponse getRentCarById(Long id) {
        RentCar rentCar = rentCarRepository.findById(id).orElseThrow(()->
                new RentCarNotFoundException("Not found such Rentcar info"));
        return rentCarConverter.toRentCarResponse(rentCar);
    }

    @Override
    public RentCarResponse createRentCarByUser(Long userId, RentCarRequest request) {
        RentCar rentCar = rentCarConverter.toRentCar(userId, request);
        RentCar savedRentCar = rentCarRepository.save(rentCar);
        return rentCarConverter.toRentCarResponse(savedRentCar);
    }

    @Override
    public RentCarResponse updateRentCarByCar(Long id, RentCarUpdateRequest request) {
        RentCar rentCar = rentCarRepository.findById(id).orElseThrow(()->
                new RentCarNotFoundException("No such rentcar info found"));
        Car car = carRepository.findById(rentCar.getId()).orElseThrow(()->
                new CarNotFoundException("No such rentcar found !"));
        rentCar.setCar(car);
        RentCar savedRentCar = rentCarRepository.save(rentCar);

        return rentCarConverter.toRentCarResponse(savedRentCar);
    }

    @Override
    public RentCarResponse updateRentCarDate(Long id, RentCarPeriodUpdateRequest request) {

        RentCar rentCar = rentCarRepository.findById(id).orElseThrow(()->
                new RentCarNotFoundException("No such rentcar found !"));
        rentCar.setStartDate(request.getStartDate());
        rentCar.setEndDate(request.getEndDate());
        RentCar savedRentCar = rentCarRepository.save(rentCar);
        return rentCarConverter.toRentCarResponse(savedRentCar);
    }

    @Override
    public void deleteRentCarById(Long id) {
        rentCarRepository.deleteById(id);
    }

    @Override
    public List<RentCarResponse> searchRentCarByUserId(Long userId) {
        List<RentCar> rentCars = rentCarRepository.findAll();

        List<RentCarResponse> rentCarResponses = rentCars.stream()
                .filter(rentCar -> {
                    Long rentCarUserId = rentCar.getUser().getId();
                    return rentCarUserId != null && rentCarUserId.equals(userId);
                })
                .map(rentCar -> rentCarConverter.toRentCarResponse(rentCar))
                .collect(Collectors.toList());

        return rentCarResponses;
    }

    @Override
    public List<RentCarResponse> searchRentCarByCarId(Long carId) {
        List<RentCar> rentCars = rentCarRepository.findAll();

        List<RentCarResponse> rentCarResponses = rentCars.stream()
                .filter(rentCar -> {
                    Long rentCarCarId = rentCar.getCar().getId();
                    return rentCarCarId != null && rentCarCarId.equals(carId);
                })
                .map(rentCar -> rentCarConverter.toRentCarResponse(rentCar))
                .collect(Collectors.toList());

        return rentCarResponses;
    }

    @Override
    public List<RentCar> findRentCarByPeriod(LocalDate start, LocalDate end) {
        return rentCarRepository.findReservationsWithinPeriod(start,end);
    }
}
