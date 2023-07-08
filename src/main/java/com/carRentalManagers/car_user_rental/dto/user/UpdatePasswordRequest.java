package com.carRentalManagers.car_user_rental.dto.user;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UpdatePasswordRequest {

    @NotBlank
    @Length(min=8)
    private String password;
}
