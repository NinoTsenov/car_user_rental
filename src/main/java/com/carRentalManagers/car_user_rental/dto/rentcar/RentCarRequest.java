package com.carRentalManagers.car_user_rental.dto.rentcar;
import jakarta.validation.constraints.NotNull;
import lombok.*;


import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class RentCarRequest {

    @NotNull
    private Long carId;
    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate endDate;
    @NotNull
    private double totalCharge;

}
