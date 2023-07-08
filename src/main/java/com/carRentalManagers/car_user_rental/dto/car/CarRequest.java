package com.carRentalManagers.car_user_rental.dto.car;

import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.NotNull;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CarRequest {

    @NotBlank
    private String brand;

    @NotBlank
    private String model;

    @NotNull
    private int numberSeats;

    @NotNull
    private double rentalPricePerDay;


 //   private List<RentCar>rentCars;
}
