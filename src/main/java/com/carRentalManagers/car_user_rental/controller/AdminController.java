package com.carRentalManagers.car_user_rental.controller;
import com.carRentalManagers.car_user_rental.dto.car.CarRequest;
import com.carRentalManagers.car_user_rental.dto.car.CarResponse;
import com.carRentalManagers.car_user_rental.dto.rentcar.RentCarResponse;
import com.carRentalManagers.car_user_rental.dto.user.UserResponse;
import com.carRentalManagers.car_user_rental.entity.Car;
import com.carRentalManagers.car_user_rental.entity.RentCar;
import com.carRentalManagers.car_user_rental.entity.User;
import com.carRentalManagers.car_user_rental.exeption.DuplicateCarException;
import com.carRentalManagers.car_user_rental.service.CarService;
import com.carRentalManagers.car_user_rental.service.RentCarService;
import com.carRentalManagers.car_user_rental.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;
    @Autowired
    CarService carService;
    @Autowired
    RentCarService rentCarService;

    //========================================== USER =================================================

    @GetMapping("/users")
    public List<User> findAllUsers () {
        return userService.findAllUsers();
    }

    @GetMapping(value = "/users", params = "email")
    public ResponseEntity<UserResponse> findUserByEmail (@RequestParam("email") String email) {
        UserResponse userResponse = userService.findUserByEmail(email);
        return ResponseEntity.status(HttpStatus.FOUND).body(userResponse);
    }

    //========================================== CARS =================================================

    @GetMapping("/cars")
    public List<Car> findAllCars () {
        return carService.findAllCars();
    }

    @GetMapping("/cars/{id}")
    public ResponseEntity<CarResponse> findCarById (@PathVariable Long id) {
        CarResponse carResponse = carService.findCarById(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(carResponse);
    }

    @PostMapping("/cars")
    public ResponseEntity<String> addNewCar (@Valid @RequestBody CarRequest request) {

        try {
            CarResponse carResponse = carService.createCar(request);
            String response = String.format("%s %s has been created", request.getBrand(), request.getModel());

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (DuplicateCarException e) {

            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @DeleteMapping("/cars/{id}")
    public ResponseEntity<Void> deleteCarById (@PathVariable Long id) {
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/cars/{id}")
    public ResponseEntity<CarResponse> updateCarById (@PathVariable Long id, @Valid @RequestBody CarRequest request) {

        CarResponse carResponse = carService.updateCar(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(carResponse);
    }

    //======================================= RESERVATIONS =====================================================

    @GetMapping("/rentals")
    public ResponseEntity<List<RentCarResponse>> findAllRentals () {
        List<RentCarResponse> response = rentCarService.getAllRentCar();
        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }

    @GetMapping("/rentals/{id}")
    public ResponseEntity<RentCarResponse> findRentalsById (@PathVariable Long id) {

        RentCarResponse rentCarResponse = rentCarService.getRentCarById(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(rentCarResponse);
    }

    @GetMapping(value = "/rentals", params = "userId")
    public ResponseEntity<List<RentCarResponse>> searchByUser (@RequestParam("userId") Long userId) {

        List<RentCarResponse> rentCarResponse = rentCarService.searchRentCarByUserId(userId);

        if (rentCarResponse.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.FOUND).body(rentCarResponse);
        }
    }

    @GetMapping(value = "/rentals", params = "carId")
    public ResponseEntity<List<RentCarResponse>> searchByCar (@RequestParam("carId") Long carId) {

        List<RentCarResponse> rentCarResponse = rentCarService.searchRentCarByCarId(carId);

        if(rentCarResponse.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.FOUND).body(rentCarResponse);
        }
    }

    @GetMapping("/rentals/period")
    public ResponseEntity<List<RentCar>> findReservationsWithinPeriod (
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        List<RentCar> rentals = rentCarService.findRentCarByPeriod(startDate, endDate);

        if(rentals.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.FOUND).body(rentals);
        }
    }
}