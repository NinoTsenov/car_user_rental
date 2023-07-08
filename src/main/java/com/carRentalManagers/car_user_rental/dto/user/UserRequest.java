package com.carRentalManagers.car_user_rental.dto.user;
import com.carRentalManagers.car_user_rental.entity.RentCar;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class UserRequest {

    @NotBlank
    @Length(min=3, message = "Name should contain at least 3 characters")
    private String firstName;

    @NotBlank
    @Length(min=3, message = "Last name should contain at least 3 characters")
    private String lastName;

    @NotBlank
    private String age;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    @Length(min = 8, message = "Password should contains at least 8 characters")
    private String password;

    @NotBlank
    @Email(message = "Enter valid email")
    private String email;

    private List<RentCar> rentCars;

}
