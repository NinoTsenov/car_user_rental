package com.carRentalManagers.car_user_rental.repository;

import com.carRentalManagers.car_user_rental.dto.rentcar.RentCarResponse;
import com.carRentalManagers.car_user_rental.entity.RentCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface RentCarRepository extends JpaRepository<RentCar, Long> {

    @Query("SELECT r FROM Reservation r JOIN r.user u WHERE u.id = :userId")
    List<RentCarResponse> searchByUser (Long userId);

    @Query("SELECT r FROM Reservation r JOIN r.car c WHERE c.id = :carId")
    List<RentCarResponse> searchByCar (Long carId);

    @Query("SELECT r FROM Reservation r WHERE r.startDate <= :endDate AND r.endDate >= :startDate")
    List<RentCar> findReservationsWithinPeriod (LocalDate startDate, LocalDate endDate);
}
