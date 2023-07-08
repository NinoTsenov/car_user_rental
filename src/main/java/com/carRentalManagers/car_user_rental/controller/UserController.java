package com.carRentalManagers.car_user_rental.controller;
import com.carRentalManagers.car_user_rental.dto.car.CarResponse;
import com.carRentalManagers.car_user_rental.dto.rentcar.RentCarPeriodUpdateRequest;
import com.carRentalManagers.car_user_rental.dto.rentcar.RentCarRequest;
import com.carRentalManagers.car_user_rental.dto.rentcar.RentCarResponse;
import com.carRentalManagers.car_user_rental.dto.rentcar.RentCarUpdateRequest;
import com.carRentalManagers.car_user_rental.dto.user.UpdatePasswordRequest;
import com.carRentalManagers.car_user_rental.dto.user.UserRequest;
import com.carRentalManagers.car_user_rental.dto.user.UserResponse;
import com.carRentalManagers.car_user_rental.dto.user.UserUpdateRequest;
import com.carRentalManagers.car_user_rental.entity.Car;
import com.carRentalManagers.car_user_rental.service.CarService;
import com.carRentalManagers.car_user_rental.service.RentCarService;
import com.carRentalManagers.car_user_rental.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    CarService carService;
    @Autowired
    RentCarService rentCarService;

    //============================================ USER =====================================================

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findUserById (@PathVariable Long id) {

        return ResponseEntity.status(HttpStatus.FOUND).body(userService.findUserById(id));
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser (@Valid @RequestBody UserRequest request) {
        UserResponse userResponse = userService.createUser(request);

        if(!(userResponse.getError() == null)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already exist");
        }

        String response = String.format("User with name %s %s has been created",
                request.getFirstName(), request.getLastName());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}/delete-account")
    public ResponseEntity<String> deleteUserById (@PathVariable Long id) {
        userService.deleteUserById(id);

        return ResponseEntity.status(HttpStatus.OK).body("User with ID: " + id + " has been deleted");
    }

    @PutMapping("/{id}/update-account")
    public ResponseEntity<UserResponse> updateUser (@PathVariable Long id,
                                                    @Valid @RequestBody UserUpdateRequest request) {

        UserResponse userResponse = userService.updateUser(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }

    @PutMapping("/{id}/update-password")
    public ResponseEntity<UserResponse> updateUserPassword (@PathVariable Long id,
                                                            @Valid @RequestBody UpdatePasswordRequest request) {

        UserResponse userResponse = userService.updatePasswordOfUser(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }

    //================================================ CARS ===========================================================

    @GetMapping("/{id}/cars")
    public List<Car> findAllCars () {
        return carService.findAllCars();
    }

    @GetMapping("/{id}/{carId}")
    public ResponseEntity<CarResponse> findCarById (@PathVariable Long carId) {
        CarResponse carResponse = carService.findCarById(carId);
        return ResponseEntity.status(HttpStatus.FOUND).body(carResponse);
    }

    //============================================== RENTACAR =====================================================

    @PostMapping("/{userId}/rentacar")
    public ResponseEntity<String> bookReservation (@PathVariable Long userId, @Valid @RequestBody RentCarRequest request) {

        RentCarResponse reservationResponse = rentCarService.createRentCarByUser(userId, request);

        String response = String.format("Your rental has been created");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{userId}/rentals/{rentCarId}")
    public ResponseEntity<RentCarResponse> findRentalById (@PathVariable Long rentalId) {

        return ResponseEntity.status(HttpStatus.FOUND).body(rentCarService.getRentCarById(rentalId));
    }

    @DeleteMapping("/{userId}/rentals/{rentalId}/delete-rental")
    public ResponseEntity<String> deleteRentalById (@PathVariable Long rentalId) {
        rentCarService.deleteRentCarById(rentalId);
        return ResponseEntity.status(HttpStatus.OK).body("Rentacar has been deleted");
    }

    @PutMapping("/{userId}/rentals/{rentalId}/update-car")
    public ResponseEntity<RentCarResponse> updateCar (@PathVariable Long rantalId,
                                                          @Valid @RequestBody RentCarUpdateRequest request) {

        RentCarResponse rentCarResponse = rentCarService.updateRentCarByCar(rantalId, request);
        return ResponseEntity.status(HttpStatus.OK).body(rentCarResponse);
    }

    @PutMapping("/{userId}/rentals/{rentyalId}/update-period")
    public ResponseEntity<RentCarResponse> updatePeriod (@PathVariable Long rentalId,
                                                             @Valid @RequestBody RentCarPeriodUpdateRequest request) {

        RentCarResponse rentCarResponse = rentCarService.updateRentCarDate(rentalId, request);
        return ResponseEntity.status(HttpStatus.OK).body(rentCarResponse);
    }
}
