package com.carRentalManagers.car_user_rental.service;
import com.carRentalManagers.car_user_rental.dto.user.UserRequest;
import com.carRentalManagers.car_user_rental.dto.user.UserResponse;
import com.carRentalManagers.car_user_rental.dto.user.UpdatePasswordRequest;
import com.carRentalManagers.car_user_rental.dto.user.UserUpdateRequest;
import com.carRentalManagers.car_user_rental.entity.User;

import java.util.List;

public interface UserService {
    UserResponse createUser(UserRequest request);
    UserResponse updateUser(Long id, UserUpdateRequest request);
    void deleteUserById(Long id);
    UserResponse findUserById(Long id);
    UserResponse findUserByEmail(String email);

    boolean existUserByEmail (String email);

    List<User> findAllUsers ();

    UserResponse updatePasswordOfUser(Long id, UpdatePasswordRequest passUpdate);
}
