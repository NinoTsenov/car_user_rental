package com.carRentalManagers.car_user_rental.exeption;

public class CarNotFoundException extends RuntimeException{

    public CarNotFoundException(String message) {
        super(message);
    }
}
