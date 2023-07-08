package com.carRentalManagers.car_user_rental.dto.rentcar;
import com.carRentalManagers.car_user_rental.dto.car.CarResponse;
import com.carRentalManagers.car_user_rental.dto.user.UserResponse;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class RentCarResponse {

    @NotNull
    private Long id;
    @NotNull
    private UserResponse user;
    @NotNull
    private CarResponse car;
    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate endDate;
    @NotNull
    private double totalCharge;

}
