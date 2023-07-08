package com.carRentalManagers.car_user_rental.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalExceptionsHandler {
    @ExceptionHandler (UserNotFoundException.class)
    public ResponseEntity<String> handlerUserNotFound(UserNotFoundException msg) {
        return new ResponseEntity<>(msg.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(CarNotFoundException.class)
    public ResponseEntity<String> handlerCarNotFound (CarNotFoundException msg){
        return new ResponseEntity<>(msg.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(DuplicateCarException.class)
    public ResponseEntity<String> handlerDuplicateCar(DuplicateCarException msg){
        return new ResponseEntity<>(msg.getMessage(),HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(RentCarNotFoundException.class)
    public ResponseEntity<String>handlerReservationNotFound(RentCarNotFoundException msg){
        return new ResponseEntity<>(msg.getMessage(),HttpStatus.NOT_FOUND);
    }
}
