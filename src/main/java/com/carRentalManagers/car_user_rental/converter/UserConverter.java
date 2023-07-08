package com.carRentalManagers.car_user_rental.converter;

import com.carRentalManagers.car_user_rental.dto.user.UserRequest;
import com.carRentalManagers.car_user_rental.dto.user.UserResponse;
import com.carRentalManagers.car_user_rental.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public User createUser (UserRequest request) {

        return User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();

    }

    public UserResponse toUserResponse (User user) {

        return new UserResponse(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail());

    }

    public UserResponse toError(String error) {

        return new UserResponse(error);
    }
}