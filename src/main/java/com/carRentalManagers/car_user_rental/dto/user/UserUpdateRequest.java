package com.carRentalManagers.car_user_rental.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserUpdateRequest {

    @NotBlank
    @Length(min=3)
    private String firstName;
    @NotBlank
    @Length(min=3)
    private String lastName;
    @Email
    private String email;
}
