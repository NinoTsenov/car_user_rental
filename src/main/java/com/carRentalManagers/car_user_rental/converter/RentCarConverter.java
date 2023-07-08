package com.carRentalManagers.car_user_rental.converter;
import com.carRentalManagers.car_user_rental.dto.rentcar.RentCarRequest;
import com.carRentalManagers.car_user_rental.dto.rentcar.RentCarResponse;
import com.carRentalManagers.car_user_rental.entity.Car;
import com.carRentalManagers.car_user_rental.entity.RentCar;
import com.carRentalManagers.car_user_rental.entity.User;
import com.carRentalManagers.car_user_rental.exeption.CarNotFoundException;
import com.carRentalManagers.car_user_rental.exeption.UserNotFoundException;
import com.carRentalManagers.car_user_rental.repository.CarRepository;
import com.carRentalManagers.car_user_rental.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
public class RentCarConverter {

    @Autowired
    UserRepository userRepository;
    @Autowired
    CarRepository carRepository;

    @Autowired
    CarConverter carConverter;

    @Autowired
    UserConverter userConverter;

     public RentCar toRentCar (Long userId,RentCarRequest request){

        User user = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("No such user found !"));
        Car car = carRepository.findById(request.getCarId()).orElseThrow(()-> new CarNotFoundException("No such car found !"));

         RentCar rentCar = RentCar.builder()
                .user(user)
                .car(car)
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                 .totalCharge(calculateTotalCharge(request.getStartDate(),request.getEndDate(),car.getRentalPricePerDay()))
                .build();
         return rentCar;
    }
    public double calculateTotalCharge (LocalDate start, LocalDate end, double dailyCharge){
        long numOfDays = ChronoUnit.DAYS.between(start, end);
        return dailyCharge * numOfDays;
    }

    public RentCarResponse toRentCarResponse (RentCar rentCar) {
        return RentCarResponse.builder()
                .id(rentCar.getId())
                .car(carConverter.toCarResponse(rentCar.getCar()))
                .user(userConverter.toUserResponse(rentCar.getUser()))
                .startDate(rentCar.getStartDate())
                .endDate(rentCar.getEndDate())
                .totalCharge(rentCar.getTotalCharge())
                .build();
    }
}
