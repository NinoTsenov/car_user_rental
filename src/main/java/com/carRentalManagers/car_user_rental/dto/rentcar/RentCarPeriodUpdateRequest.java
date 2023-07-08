package com.carRentalManagers.car_user_rental.dto.rentcar;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class RentCarPeriodUpdateRequest {
    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate endDate;
}
