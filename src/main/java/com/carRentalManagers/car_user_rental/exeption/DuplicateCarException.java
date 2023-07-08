package com.carRentalManagers.car_user_rental.exeption;

public class DuplicateCarException extends RuntimeException{
    public DuplicateCarException (String message){
        super(message);
    }
}
