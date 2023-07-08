package com.carRentalManagers.car_user_rental.converter;

import com.carRentalManagers.car_user_rental.dto.car.CarRequest;
import com.carRentalManagers.car_user_rental.dto.car.CarResponse;
import com.carRentalManagers.car_user_rental.entity.Car;
import org.springframework.stereotype.Component;

@Component
public class CarConverter {

    public Car toCar(CarRequest carRequest){

        return Car.builder()
                .brand(carRequest.getBrand())
                .model(carRequest.getModel())
                .numberSeats(carRequest.getNumberSeats())
                .rentalPricePerDay(carRequest.getRentalPricePerDay())
                .build();
    }
    public CarResponse toCarResponse (Car car){
        return new CarResponse(car.getId(),car.getBrand(),car.getModel(),
                car.getNumberSeats(),car.getRentalPricePerDay());
    }
}
