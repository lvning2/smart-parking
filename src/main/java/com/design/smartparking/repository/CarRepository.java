package com.design.smartparking.repository;

import com.design.smartparking.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car,Long> {

    List<Car> findAllByUserId(Long userId);

}
