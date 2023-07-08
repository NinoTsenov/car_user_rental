package com.carRentalManagers.car_user_rental.dto.user;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class UserResponse {

     private Long id;
     @NotBlank
     private String firstName;
     @NotBlank
     private String lastName;
     @Email
     private String email;
     @JsonIgnore
     private String error;

     public UserResponse(String error) {
          this.error = error;
     }

     public UserResponse(Long id, String firstName, String lastName, String email) {
          this.id = id;
          this.firstName = firstName;
          this.lastName = lastName;
          this.email = email;
     }
}
