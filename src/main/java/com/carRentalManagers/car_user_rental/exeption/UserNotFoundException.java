package com.carRentalManagers.car_user_rental.exeption;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String message) {
        super(message);
    }
}
