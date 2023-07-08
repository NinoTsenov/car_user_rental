package com.carRentalManagers.car_user_rental.service.impl;
import com.carRentalManagers.car_user_rental.converter.UserConverter;
import com.carRentalManagers.car_user_rental.dto.user.UpdatePasswordRequest;
import com.carRentalManagers.car_user_rental.dto.user.UserRequest;
import com.carRentalManagers.car_user_rental.dto.user.UserResponse;
import com.carRentalManagers.car_user_rental.dto.user.UserUpdateRequest;
import com.carRentalManagers.car_user_rental.entity.User;
import com.carRentalManagers.car_user_rental.exeption.UserNotFoundException;
import com.carRentalManagers.car_user_rental.repository.UserRepository;
import com.carRentalManagers.car_user_rental.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private  final UserRepository userRepository;
    private final UserConverter userConverter;



    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserConverter userConverter, UserServiceImpl userService) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;

    }

    @Override
    public UserResponse createUser(UserRequest request) {

        if (existUserByEmail(request.getEmail())) {
            return userConverter.toError("User with the same email already exists");
        }

        User user = userConverter.createUser(request);
        User savedUser = userRepository.save(user);

        return userConverter.toUserResponse(savedUser);
    }

    @Override
    public UserResponse updateUser(Long id, UserUpdateRequest request) {

        User user = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("User not exist"));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(user.getEmail());

        User savedUser = userRepository.save(user);
        return userConverter.toUserResponse(savedUser);
    }


    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);

    }

    @Override
    public UserResponse findUserById(Long id) {

        User user = userRepository.findById(id).orElseThrow(()->
                new RuntimeException("User with ID " + id + " not found."));
        return userConverter.toUserResponse(user);
    }

    @Override
    public UserResponse findUserByEmail(String email) {

        User user = userRepository.findByEmail(email).orElseThrow(()-> new UserNotFoundException("User not found"));
        return userConverter.toUserResponse(user);
    }

    @Override
    public boolean existUserByEmail(String email) {
        return userRepository.findAll().stream().anyMatch(user -> user.getEmail().equals(email));

    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserResponse updatePasswordOfUser(Long id, UpdatePasswordRequest passUpdate) {
        User user = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("User not found"));
        user.setPassword(passUpdate.getPassword());
        User savedUser = userRepository.save(user);
        return userConverter.toUserResponse(savedUser);
    }
}
