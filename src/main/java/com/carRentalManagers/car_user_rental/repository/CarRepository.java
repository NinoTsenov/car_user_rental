package com.carRentalManagers.car_user_rental.repository;

import com.carRentalManagers.car_user_rental.entity.Car;

import org.springframework.data.jpa.repository.JpaRepository;



public interface CarRepository extends JpaRepository<Car, Long> {

}
