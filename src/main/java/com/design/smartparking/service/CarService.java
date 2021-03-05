package com.design.smartparking.service;

import com.design.smartparking.model.Car;
import com.design.smartparking.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public List<Car> list(Long userId){
        return carRepository.findAllByUserId(userId);
    }

    @Transactional
    public void delete(Long id){
        carRepository.deleteById(id);
    }

}
