package com.carRentalManagers.car_user_rental.service;
import com.carRentalManagers.car_user_rental.dto.rentcar.RentCarPeriodUpdateRequest;
import com.carRentalManagers.car_user_rental.dto.rentcar.RentCarRequest;
import com.carRentalManagers.car_user_rental.dto.rentcar.RentCarResponse;
import com.carRentalManagers.car_user_rental.dto.rentcar.RentCarUpdateRequest;
import com.carRentalManagers.car_user_rental.entity.RentCar;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;


@Service
public interface RentCarService {


    List<RentCarResponse> getAllRentCar();

    RentCarResponse getRentCarById(Long id);
    RentCarResponse createRentCarByUser (Long userId,RentCarRequest request);

    RentCarResponse updateRentCarByCar(Long carId, RentCarUpdateRequest request);

    RentCarResponse updateRentCarDate(Long id, RentCarPeriodUpdateRequest request);

    void deleteRentCarById (Long id);

    List<RentCarResponse> searchRentCarByUserId(Long userId);

    List<RentCarResponse> searchRentCarByCarId(Long carId);

    List<RentCar> findRentCarByPeriod(LocalDate start, LocalDate end);

}
