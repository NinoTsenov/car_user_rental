package com.carRentalManagers.car_user_rental.exeption;

public class RentCarNotFoundException extends RuntimeException{
    public RentCarNotFoundException(String message){
        super(message);
    }
}
