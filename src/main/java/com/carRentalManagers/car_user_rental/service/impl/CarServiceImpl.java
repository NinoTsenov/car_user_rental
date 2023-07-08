package com.carRentalManagers.car_user_rental.service.impl;
import com.carRentalManagers.car_user_rental.converter.CarConverter;
import com.carRentalManagers.car_user_rental.dto.car.CarRequest;
import com.carRentalManagers.car_user_rental.dto.car.CarResponse;
import com.carRentalManagers.car_user_rental.entity.Car;
import com.carRentalManagers.car_user_rental.exeption.CarNotFoundException;
import com.carRentalManagers.car_user_rental.exeption.DuplicateCarException;
import com.carRentalManagers.car_user_rental.repository.CarRepository;
import com.carRentalManagers.car_user_rental.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final CarConverter carConverter;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, CarConverter carConverter) {
        this.carRepository = carRepository;
        this.carConverter = carConverter;
    }


    @Override
    public CarResponse createCar(CarRequest carRequest) {

        Car car = carConverter.toCar(carRequest);
        Car savedCar;
        try {
            savedCar = carRepository.save(car);
        }catch (DataIntegrityViolationException e){
            throw new DuplicateCarException("Car already exist");
        }
        return carConverter.toCarResponse(savedCar);
    }

    @Override
    public CarResponse findCarById(Long id) {
        Car car = carRepository.findById(id).orElseThrow(()->new RuntimeException("Car with ID " + id + " not found."));
        return carConverter.toCarResponse(car);
    }

    @Override
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public List<Car> findAllCars() {

        return carRepository.findAll();
    }

    @Override
    public CarResponse updateCar(Long id, CarRequest carRequest) {

        Car car = carRepository.findById(id).orElseThrow(()->
                new CarNotFoundException("Car with ID " + id + " not found."));

         car.setBrand(car.getBrand());
         car.setModel(car.getModel());
         car.setNumberSeats(car.getNumberSeats());
         car.setRentalPricePerDay(car.getRentalPricePerDay());

         return carConverter.toCarResponse(carRepository.save(car));
    }
}
