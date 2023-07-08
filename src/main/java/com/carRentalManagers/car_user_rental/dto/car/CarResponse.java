package com.carRentalManagers.car_user_rental.dto.car;

import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.NotNull;
import lombok.*;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class CarResponse {

    @NotNull
    private Long id;

    @NotBlank
    private String brand;

    @NotBlank
    private String model;

    @NotNull
    private int numberSeats;

    @NotNull
    private double rentalPricePerDay;

  //  private List<RentCar> rentCars;


}
