package com.carRentalManagers.car_user_rental.service;

import com.carRentalManagers.car_user_rental.dto.car.CarRequest;
import com.carRentalManagers.car_user_rental.dto.car.CarResponse;
import com.carRentalManagers.car_user_rental.entity.Car;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarService {

    CarResponse createCar (CarRequest carRequest);
    CarResponse findCarById (Long id);
    void deleteCar(Long id);
    List<Car> findAllCars();
    CarResponse updateCar (Long id, CarRequest carRequest);


}
