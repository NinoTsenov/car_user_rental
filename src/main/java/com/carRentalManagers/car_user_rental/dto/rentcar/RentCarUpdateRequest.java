package com.carRentalManagers.car_user_rental.dto.rentcar;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class RentCarUpdateRequest {
    @NotNull
    private Long carId;

}
